import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    java
    id("dev.architectury.loom") version "1.10-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

architectury {
    minecraft = mod.minecraft_version
}

allprojects {
    group = mod.group
    version = "${mod.version}+mc${mod.minecraft_version}"
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")

    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")

    loom.silentMojangMappingsLicense()

    base.archivesName.set("${mod.id}-${project.name}")

    repositories {
        flatDir { dirs("mods") }

        maven {
            name = "ParchmentMC"
            url = uri("https://maven.parchmentmc.org")
        }
        maven {
            url = uri("https://cursemaven.com")
            content { includeGroup("curse.maven") }
        }
        maven {
            url = uri("https://api.modrinth.com/maven")
            content { includeGroup("maven.modrinth") }
        }
        maven {
            name = "Cloth Config"
            url = uri("https://maven.shedaniel.me/")
        }
        maven {
            name = "vazkii"
            url = uri("https://maven.blamejared.com")
            content {
                includeGroup("vazkii.patchouli")
                includeGroup("vazkii.botania")
            }
        }
    }

    dependencies {
        "minecraft"("net.minecraft:minecraft:${mod.minecraft_version}")
        @Suppress("UnstableApiUsage", "SpellCheckingInspection")
        "mappings"(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${mod.minecraft_version}:${mod.prop("parchment_version")}@zip")
        })

        implementation("blue.endless:jankson:1.2.3")

        compileOnly("org.projectlombok:lombok:1.18.38")
        annotationProcessor("org.projectlombok:lombok:1.18.38")
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

    tasks.processResources {
        from(rootProject.file("LICENSE"))
        from(rootProject.file("third-party-licenses")) { into("third-party-licenses") }
    }
}
