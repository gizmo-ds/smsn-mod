@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    alias(libs.plugins.shadow)
}

apply(plugin = "com.hypherionmc.modutils.modpublisher")

architectury { forge() }

loom {
    forge {
        mixinConfig("smsn.mixins.json")
    }
}

val shadowBundle: Configuration by configurations.getting
val developmentForge: Configuration by configurations.getting
configurations {
    developmentForge.extendsFrom(common.get())
}

repositories {
    maven("https://maven.tterrag.com/") { name = "Create" }
    maven("https://maven.theillusivec4.top/") { name = "Curios" }
    maven("https://maven.teamabnormals.com") { name = "Blueprint" }
    maven("https://maven.blamejared.com/") {
        name = "Immersive Engineering"
        content {
            includeGroup("malte0811")
            includeGroup("blusunrize.immersiveengineering")
        }
    }
    maven("https://maven.shadowsoffire.dev/releases") {
        content {
            includeGroup("dev.shadowsoffire")
        }
    }
}

dependencies {
    forge(libs.forge.forge)

    localRuntime(libs.mixinextras.forge)
    modLocalRuntime(libs.norealmsbutton.forge)

    modApi(libs.clothconfig.forge)
//    modCompileOnly(libs.clothconfig.forge)

    // Quark
    modLocalRuntime(libs.forge.zeta)
    modImplementation(libs.forge.quark)
    // Alex's mods
    modImplementation(libs.forge.citadel)
    modImplementation(libs.forge.alexscaves)
    // Xaero's maps
    modImplementation(libs.forge.xaeros.minimap)
    modImplementation(libs.forge.xaeros.worldmap)
    // Petrolpark
    modCompileOnly(libs.forge.petrolpark)
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly(libs.forge.ipn)
    // Obscure API
    modImplementation(libs.forge.obscureapi)
    // Blueprint
    modImplementation(libs.forge.blueprint)
    // Aether Nitrogen
    modImplementation(libs.forge.nitrogen)
    // Bagus lib
    modImplementation(libs.forge.baguslib)
    // Immersive Engineering
    modImplementation(libs.forge.immersiveengineering)
    // Enigmatic Legacy
    modLocalRuntime(libs.forge.curios)
    modLocalRuntime(libs.forge.caelus)
    modLocalRuntime(libs.forge.patchouli)
    modImplementation(libs.forge.enigmaticlegacy)
    // Supplementaries
    modLocalRuntime(libs.forge.moonlight)
    modImplementation(libs.forge.supplementaries)
    // Placebo
    modImplementation(libs.forge.placebo)
    // Immersive Caves
    modImplementation(libs.forge.immersivecaves)
    // Ad Astra!
    modCompileOnly(libs.forge.adastra)
    // Exposure
    modImplementation(libs.forge.exposure)
    // Titanium
    modImplementation(libs.forge.titanium)
    // Ribbits
    modCompileOnly(libs.forge.ribbits)
    // M.R.U
    modImplementation(libs.forge.mru)
    // Botania
    modImplementation(libs.forge.botania)
    // ProjectE
    modImplementation(libs.forge.projecte)
    // Hexerei
    modCompileOnly(libs.forge.hexerei)
    // Voidscape
    modImplementation(libs.forge.voidscape)
    // TenshiLib
    modImplementation(libs.forge.tenshilib)
    // AdditionalStructures
    modImplementation(libs.forge.additionalstructures)
    // Create Tram Additions
    modCompileOnly(libs.forge.createtramadditions)
    // Ars Nouveau
    modCompileOnly(libs.forge.arsnouveau)
    // Immersive Portals
    modCompileOnly(libs.forge.immersiveportals)
    // Majrusz
    modCompileOnly(libs.forge.majrusz.library)
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/mods.toml") { expand("version" to project.version) }
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