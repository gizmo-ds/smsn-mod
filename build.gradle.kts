@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    java
    alias(libs.plugins.loom) apply false
    alias(libs.plugins.architectury)
    alias(libs.plugins.shadow) apply false
}

architectury { minecraft = mod.mc_version }

allprojects {
    group = mod.group
    version = "${mod.version}-${mod.mc_version}"
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")

    base.archivesName.set("${mod.id}-${project.name}")

    val libs = rootProject.libs
    var mappingsDependency: Dependency? = null
    configure<LoomGradleExtensionAPI> {
        silentMojangMappingsLicense()

        mappingsDependency = layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${mod.mc_version}:${libs.versions.parchment.get()}@zip")
        }
    }

    repositories {
        flatDir { dirs("mods") }

        maven("https://maven.parchmentmc.org") { name = "ParchmentMC" }
        maven("https://jitpack.io") { name = "JitPack" }
        maven("https://cursemaven.com") {
            content { includeGroup("curse.maven") }
        }
        maven("https://api.modrinth.com/maven") {
            content { includeGroup("maven.modrinth") }
        }
        maven("https://maven.shedaniel.me/") { name = "Cloth Config" }
        maven("https://maven.blamejared.com") {
            name = "vazkii"
            content {
                includeGroup("blusunrize.immersiveengineering")
                includeGroup("vazkii.patchouli")
                includeGroup("vazkii.botania")
                includeGroup("org.violetmoon.quark")
                includeGroup("org.violetmoon.zeta")
            }
        }
        maven("https://packages.aether-mod.net/Nitrogen") { name = "Aether Teams" }
        maven("https://mvn.devos.one/snapshots/") {
            name = "Porting Lib"
            content { includeGroup("io.github.fabricators_of_create.Porting-Lib") }
        }
    }

    dependencies {
        "minecraft"("net.minecraft:minecraft:${mod.mc_version}")
        mappingsDependency?.let { "mappings"(it) }

        compileOnly(rootProject.libs.mixinextras.common)
        annotationProcessor(rootProject.libs.mixinextras.common)

        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)
    }

    java {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    tasks.named("clean") {
        doLast { delete("logs") }
    }
}

configure(mod.enabled_platforms.map { project(":$it") }) {
    apply(plugin = "architectury-plugin")
    apply(plugin = "dev.architectury.loom")

    val platformName = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
        .platform.map { it.displayName() }.get()

    configure<dev.architectury.plugin.ArchitectPluginExtension> {
        platformSetupLoomIde()
    }

    val common: Configuration by configurations.creating
    val shadowBundle: Configuration by configurations.creating
    configurations {
        compileOnly.configure { extendsFrom(common) }
        runtimeOnly.configure { extendsFrom(common) }

        shadowBundle.isCanBeResolved = true
        shadowBundle.isCanBeConsumed = false
    }

    dependencies {
        common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
        shadowBundle(project(path = ":common", configuration = "transformProduction$platformName"))
    }

    tasks {
        processResources {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE

            from(rootProject.file("LICENSE")) { rename { "LICENSE.txt" } }
            from(rootProject.file("third-party-licenses")) { into("third-party-licenses") }
            from(project.file("third-party-licenses")) { into("third-party-licenses") }
            from(rootProject.file("assets/logo.png")) { rename { "${mod.id}_logo.png" } }
            from(rootProject.file("assets/private-logo.png")) { rename { "${mod.id}_logo.png" } }
        }
    }
}