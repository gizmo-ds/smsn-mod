@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    alias(libs.plugins.shadow)
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
    maven("https://maven.terraformersmc.com/") { name = "Terraformers" }
    maven("https://maven.ladysnake.org/releases") { name = "trinkets" }
}

dependencies {
    modImplementation(libs.fabric.loader)

    localRuntime(libs.mixinextras.fabric)
    modLocalRuntime(libs.fabric.api)
    modLocalRuntime(libs.lazydfu.fabric)

    modApi(libs.clothconfig.fabric) {
        exclude(group = "net.fabricmc.fabric-api")
        exclude(group = "net.fabricmc", module = "fabric-loader")
    }
//    modCompileOnly(libs.clothconfig.fabric)
    modImplementation(libs.fabric.modmenu)

    // Xaero's maps
    modImplementation(libs.fabric.xaeros.minimap)
    modImplementation(libs.fabric.xaeros.worldmap)
    // Botania
    modImplementation(libs.fabric.botania) { exclude(group = "com.jamieswhiteshirt") }
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly(libs.fabric.ipn)
    // Iris
    modCompileOnly(libs.fabric.iris)

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