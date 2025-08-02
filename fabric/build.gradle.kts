@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    id("com.github.johnrengelman.shadow")
}

apply(plugin = "com.modrinth.minotaur")
apply(plugin = "net.darkhax.curseforgegradle")

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

    localRuntime("io.github.llamalad7:mixinextras-fabric:${mod.prop("mixinextras")}")

    modApi("me.shedaniel.cloth:cloth-config-fabric:${mod.prop("cloth_config")}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
//    modCompileOnly("me.shedaniel.cloth:cloth-config-neoforge:${mod.prop("cloth_config")}")
    modImplementation("com.terraformersmc:modmenu:${mod.prop("fabric.modmenu")}")

    modLocalRuntime("net.fabricmc.fabric-api:fabric-api:${mod.prop("fabric.api")}")

    // Xaero's maps
    modImplementation("maven.modrinth:xaeros-minimap:${mod.prop("fabric.xaeros_minimap")}")
    modImplementation("maven.modrinth:xaeros-world-map:${mod.prop("fabric.xaeros_world_map")}")
    // Supplementaries
    modCompileOnly("maven.modrinth:moonlight:${mod.prop("fabric.moonlight")}")
    modCompileOnly("maven.modrinth:supplementaries:${mod.prop("fabric.supplementaries")}")
    // Botania
    modImplementation("vazkii.botania:Botania:${mod.prop("fabric.botania")}") {
        exclude(group = "com.jamieswhiteshirt")
    }
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly("maven.modrinth:inventory-profiles-next:${mod.prop("fabric.ipn")}")
    // Iris
    modLocalRuntime("maven.modrinth:sodium:${mod.prop("fabric.sodium")}")
    modImplementation("maven.modrinth:iris:${mod.prop("fabric.iris")}")
    // Ad Astra!
    modCompileOnly("maven.modrinth:ad-astra:${mod.prop("fabric.ad_astra")}")
    // Exposure
    modCompileOnly("maven.modrinth:exposure:${mod.prop("fabric.exposure")}")
    // Ribbits
    modCompileOnly("maven.modrinth:ribbits:${mod.prop("fabric.ribbits")}")
    // M.R.U
    modImplementation("maven.modrinth:mru:${mod.prop("fabric.mru")}")

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
    }

    remapJar {
        inputFile.set(shadowJar.flatMap { it.archiveFile })
        dependsOn(shadowJar)
    }

    if (mod.modrinth_id.isNotEmpty() && (ext.get("modrinth_token") as String).isNotEmpty())
        modrinth { uploadFile.set(remapJar.flatMap { it.archiveFile }) }
    if (mod.curseforge_id.isNotEmpty() && (ext.get("curseforge_token") as String).isNotEmpty())
        curseforge {
            val mainFile = upload(mod.curseforge_id, remapJar.flatMap { it.archiveFile })
            mainFile.releaseType = mod.release_type
            mainFile.gameVersions.addAll(mod.game_version_supports)
            mainFile.addModLoader(project.name)
            mainFile.addOptional("cloth-config")
            mainFile.changelog = ext.get("changelog")
        }
}