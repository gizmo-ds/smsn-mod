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
}

dependencies {
    modImplementation(libs.fabric.loader)

    localRuntime(libs.mixinextras.fabric)
    modLocalRuntime(libs.fabric.api)
    modLocalRuntime(libs.fabric.norealmsbutton)

    modImplementation(libs.fabric.modmenu)
    modApi(libs.clothconfig.fabric) { exclude(group = "net.fabricmc.fabric-api") }
//    modCompileOnly(libs.clothconfig.fabric)

    // Supplementaries
    modLocalRuntime(libs.fabric.moonlight)
    modImplementation(libs.fabric.supplementaries)
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly(libs.fabric.ipn)
    // Iris Shader
    modLocalRuntime(libs.fabric.sodium)
    modImplementation(libs.fabric.iris)
    // Exposure
    modLocalRuntime(libs.fabric.forgeconfigapiport)
    modImplementation(libs.fabric.exposure)
    // Aether Nitrogen
    modImplementation(libs.fabric.nitrogen)
    // Ribbits
    modCompileOnly(libs.fabric.ribbits)
    // Xaero's Maps
    modImplementation(libs.fabric.xaeros.minimap)
    modImplementation(libs.fabric.xaeros.worldmap)
    // M.R.U
    modImplementation(libs.fabric.mru)
    // TenshiLib
    modImplementation(libs.fabric.tenshilib)

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
            mainFile.changelog = ext.get("changelog")
            mainFile.addOptional("cloth-config")
        }
}