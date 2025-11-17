@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    alias(libs.plugins.shadow)
}

apply(plugin = "com.hypherionmc.modutils.modpublisher")

architectury { fabric() }

val shadowBundle: Configuration by configurations.getting
val developmentFabric: Configuration by configurations.getting
configurations {
    developmentFabric.extendsFrom(common.get())
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
    // Immersive Portals
    modCompileOnly(libs.fabric.immersiveportals)
    // Kiwi
    modImplementation(libs.fabric.kiwi)
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") { expand("version" to project.version) }
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

    publisher { artifact.set(remapJar) }
}