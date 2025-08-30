@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    java
    alias(libs.plugins.loom) apply false
    alias(libs.plugins.architectury)
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.dotenv)
    alias(libs.plugins.curseforge) apply false
    alias(libs.plugins.modrinth) apply false
}

architectury {
    minecraft = mod.minecraft_version
}

allprojects {
    group = mod.group
    version = "${mod.version}-${mod.minecraft_version}"
}

val curseforgeToken: String = env.fetch("CF_TOKEN", "").trim()
val modrinthToken: String = env.fetch("MODRINTH_TOKEN", "").trim()
val modChangelog = rootProject.file("CHANGELOG.md").readText().split("###")[1].let { x -> "###$x".trim() }

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")

    val loom = project.extensions.getByName<net.fabricmc.loom.api.LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    base.archivesName.set("${mod.id}-${project.name}")

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
        maven("https://modmaven.dev") { name = "Just Enough Items" }
        maven("https://packages.aether-mod.net/Nitrogen") { name = "Aether Team" }
        maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/") {
            name = "Fuzss"
            content { includeGroup("fuzs.forgeconfigapiport") }
        }
    }

    dependencies {
        "minecraft"("net.minecraft:minecraft:${mod.minecraft_version}")
        "mappings"(loom.officialMojangMappings())

        compileOnly(rootProject.libs.mixinextras.common)
        annotationProcessor(rootProject.libs.mixinextras.common)

        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)
    }

    java {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    tasks.processResources {
        from(rootProject.file("LICENSE"))
        from(rootProject.file("third-party-licenses")) { into("third-party-licenses") }
    }

    if (mod.enabled_platforms.contains(project.name)) {
        apply(plugin = "com.modrinth.minotaur")
        apply(plugin = "net.darkhax.curseforgegradle")

        ext.set("changelog", modChangelog)
        ext.set("curseforge_token", curseforgeToken)
        ext.set("modrinth_token", modrinthToken)

        if (mod.modrinth_id.isNotEmpty() && modrinthToken.isNotEmpty())
            extensions.configure<com.modrinth.minotaur.ModrinthExtension>("modrinth") {
                debugMode.set(mod.debug_publishing || !mod.publish_platforms.contains(project.name))
                token.set(modrinthToken)
                projectId.set(mod.modrinth_id)
                syncBodyFrom.set(rootProject.file("README.md").readText())
                versionName.set("${mod.version} ${loom.platform.get().displayName()}")
                versionNumber.set("${project.name}-${project.version}")
                versionType.set(mod.release_type)
                gameVersions.addAll(mod.game_version_supports)
                loaders.add(project.name)
                changelog.set(modChangelog)
                dependencies {
                    optional.project("cloth-config")
                }
            }
        tasks.register<net.darkhax.curseforgegradle.TaskPublishCurseForge>("curseforge") {
            if (mod.curseforge_id.isEmpty() || curseforgeToken.isEmpty()) {
                isEnabled = false
                return@register
            }
            group = "publishing"
            debugMode = mod.debug_publishing || !mod.publish_platforms.contains(project.name)
            apiToken = curseforgeToken
        }
        tasks.register("releaseMod") {
            group = "publishing"

            dependsOn("curseforge")
            dependsOn("modrinth")
        }
    }
}
