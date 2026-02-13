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
    maven("https://maven.ladysnake.org/releases") { name = "trinkets" }
}

dependencies {
    modImplementation(libs.fabric.loader)

    localRuntime(libs.mixinextras.fabric)
    modLocalRuntime(libs.norealmsbutton.fabric)
    modLocalRuntime(libs.fabric.api)

    modApi(libs.clothconfig.fabric) { exclude(group = "net.fabricmc.fabric-api") }
//    modCompileOnly(libs.clothconfig.fabric)
    modImplementation(libs.fabric.modmenu)

    // Xaero's maps
    modImplementation(libs.fabric.xaeros.minimap)
    modImplementation(libs.fabric.xaeros.worldmap)
    // Supplementaries
    modCompileOnly(libs.fabric.supplementaries)
    // Botania
    modImplementation(libs.fabric.botania) { exclude(group = "com.jamieswhiteshirt") }
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly(libs.fabric.ipn)
    // Iris
    modLocalRuntime(libs.fabric.sodium)
    modImplementation(libs.fabric.iris)
    // Ad Astra!
    modCompileOnly(libs.fabric.adastra)
    // Exposure
    modCompileOnly(libs.fabric.exposure)
    // Ribbits
    modCompileOnly(libs.fabric.ribbits)
    // M.R.U
    modImplementation(libs.fabric.mru)
    // Aether Nitrogen
    modImplementation(libs.fabric.nitrogen)
    // TenshiLib
    modImplementation(libs.fabric.tenshilib)
    // Create Tram Additions
    modCompileOnly(libs.fabric.createtramadditions)
    // Immersive Portals
    modCompileOnly(libs.fabric.immersiveportals) { exclude(group = "net.fabricmc.fabric-api") }
    // Majrusz
    modCompileOnly(libs.fabric.majrusz.library)
    // Kiwi
    modImplementation(libs.fabric.kiwi)
    // Create: Steam 'n' Rails
    modCompileOnly(libs.fabric.create.snr)
    // UnionLib
    localRuntime("org.tomlj:tomlj:1.1.1")
    modImplementation(libs.fabric.unionlib)
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