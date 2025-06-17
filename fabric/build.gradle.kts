@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    id("com.github.johnrengelman.shadow")
    id("me.shedaniel.unified-publishing") version "0.1.+"
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val common: Configuration by configurations.creating
val shadowBundle: Configuration by configurations.creating
val developmentFabric: Configuration by configurations.getting

configurations {
    compileOnly.configure { extendsFrom(common) }
    runtimeOnly.configure { extendsFrom(common) }
    developmentFabric.extendsFrom(common)

    shadowBundle.isCanBeResolved = true
    shadowBundle.isCanBeConsumed = false
}

repositories {
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/")
    }
    maven {
        name = "trinkets"
        url = uri("https://maven.ladysnake.org/releases")
    }
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${mod.prop("fabric.loader")}")

    implementation(include("io.github.llamalad7:mixinextras-fabric:0.4.1")!!)

    modApi("me.shedaniel.cloth:cloth-config-fabric:${mod.prop("cloth_config")}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modImplementation("com.terraformersmc:modmenu:7.2.2")

    modLocalRuntime("net.fabricmc.fabric-api:fabric-api:${mod.prop("fabric.api")}")

    // Xaero's maps
    modImplementation("maven.modrinth:xaeros-minimap:${mod.prop("fabric.xaeros_minimap")}")
    modImplementation("maven.modrinth:xaeros-world-map:${mod.prop("fabric.xaeros_world_map")}")
    // Supplementaries
    modCompileOnly("maven.modrinth:moonlight:dOeX0aw2")
    modCompileOnly("maven.modrinth:supplementaries:${mod.prop("fabric.supplementaries")}")
    // Botania
    modImplementation("vazkii.botania:Botania:${mod.prop("fabric.botania")}") {
        exclude(group = "com.jamieswhiteshirt")
    }
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly("maven.modrinth:inventory-profiles-next:${mod.prop("fabric.ipn")}")
    // Iris
    modLocalRuntime("maven.modrinth:sodium:vgceLbdH")
    modImplementation("maven.modrinth:iris:${mod.prop("fabric.iris")}")
    // Ad Astra!
//    modCompileOnly("mods:ad_astra:fabric-1.20.1-1.15.3")
    modCompileOnly("maven.modrinth:ad-astra:${mod.prop("fabric.ad_astra")}")

    shadowBundle("blue.endless:jankson:1.2.3")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowBundle(project(path = ":common", configuration = "transformProductionFabric"))
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
        from(rootProject.file("assets/icon.png")) {
            rename { "assets/${mod.id}/icon.png" }
        }
    }

    shadowJar {
        configurations = listOf(shadowBundle)
        archiveClassifier.set("dev-shadow")

        mergeServiceFiles()

        relocate("blue.endless.jankson", "${mod.group}.${mod.id}.libs.jankson")
    }

    remapJar {
        inputFile.set(shadowJar.flatMap { it.archiveFile })
        dependsOn(shadowJar)
    }
}

unifiedPublishing {
    project {
        version.set("${mod.version}+mc${mod.minecraft_version}")
        displayName.set("${mod.name} v${mod.version}")
        gameVersions.add(mod.minecraft_version)
        gameLoaders.set(listOf("fabric"))
        releaseType.set(mod.release_type)

        mainPublication.set(tasks.remapJar.flatMap { it.archiveFile })

        val modrinthToken: String = env.fetch("MODRINTH_TOKEN", "").trim()
        val modrinthId: String = mod.prop("modrinth_id")
        if (modrinthId.isNotEmpty() && modrinthToken.isNotEmpty()) {
            modrinth {
                token.set(modrinthToken)
                id.set(modrinthId)

                relations {
                    optionals.add("cloth-config")
                }
            }
        }

        val curseforgeToken: String = env.fetch("CF_TOKEN", "").trim()
        val curseforgeId: String = mod.prop("curseforge_id")
        if (curseforgeId.isNotEmpty() && curseforgeToken.isNotEmpty()) {
            curseforge {
                token.set(curseforgeToken)
                id.set(curseforgeId)

                relations {
                    optionals.add("cloth-config")
                }
            }
        }
    }
}