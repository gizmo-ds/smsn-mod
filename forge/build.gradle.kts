@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    id("com.github.johnrengelman.shadow")
}

loom {
    forge {
        mixinConfig("smsn.mixins.json")
    }
}

architectury {
    platformSetupLoomIde()
    forge()
}

val common: Configuration by configurations.creating
val shadowBundle: Configuration by configurations.creating
val developmentForge: Configuration by configurations.getting

configurations {
    compileOnly.configure { extendsFrom(common) }
    runtimeOnly.configure { extendsFrom(common) }
    developmentForge.extendsFrom(common)

    shadowBundle.isCanBeResolved = true
    shadowBundle.isCanBeConsumed = false
}

@Suppress("SpellCheckingInspection")
repositories {
    maven {
        name = "Create"
        url = uri("https://maven.tterrag.com/")
    }
    maven {
        name = "Aether Teams"
        url = uri("https://packages.aether-mod.net/Nitrogen")
    }
    maven {
        name = "Curios"
        url = uri("https://maven.theillusivec4.top/")
    }
    maven {
        name = "Blueprint"
        url = uri("https://maven.teamabnormals.com")
    }
    maven {
        name = "Immersive Engineering"
        url = uri("https://maven.blamejared.com/")
        content {
            includeGroup("malte0811")
            includeGroup("blusunrize.immersiveengineering")
        }
    }
}

dependencies {
    forge("net.minecraftforge:forge:${mod.prop("forge.version")}")

    modApi("me.shedaniel.cloth:cloth-config-forge:${mod.prop("cloth_config")}")

    localRuntime("io.github.llamalad7:mixinextras-forge:0.3.6")

    // Quark
    modLocalRuntime("maven.modrinth:zeta:VDiwJ2Xr")
    modImplementation("maven.modrinth:quark:${mod.prop("forge.quark")}")
    // Alex's mods
    modImplementation("maven.modrinth:citadel:${mod.prop("forge.citadel")}")
    modImplementation("maven.modrinth:alexs-caves:${mod.prop("forge.alexs_caves")}")
    // Xaero's maps
    modImplementation("maven.modrinth:xaeros-minimap:${mod.prop("forge.xaeros_minimap")}")
    modImplementation("maven.modrinth:xaeros-world-map:${mod.prop("forge.xaeros_world_map")}")
    // Petrolpark
    modLocalRuntime("com.tterrag.registrate:Registrate:MC1.20-1.3.3")
    modLocalRuntime("com.jozufozu.flywheel:flywheel-forge-1.20.1:0.6.11-13")
    modLocalRuntime("com.simibubi.create:create-1.20.1:0.5.1.h-48:slim") {
        isTransitive = false
    }
    modImplementation("maven.modrinth:petrolpark:${mod.prop("forge.petrolpark")}")
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly("maven.modrinth:inventory-profiles-next:${mod.prop("forge.ipn")}")
    // Obscure API
    modImplementation("maven.modrinth:obscure-api:${mod.prop("forge.obscure_api")}")
    // Blueprint
    modImplementation("com.teamabnormals:blueprint:${mod.prop("forge.blueprint")}")
    // Aether Nitrogen
    modImplementation("com.aetherteam.nitrogen:nitrogen_internals:${mod.prop("forge.nitrogen")}")
    // Bagus lib
    modImplementation("maven.modrinth:bagus-lib:${mod.prop("forge.bagus_lib")}")
    // Immersive Engineering
    modImplementation("maven.modrinth:immersiveengineering:${mod.prop("forge.immersive_engineering")}")
    // Enigmatic Legacy
    modLocalRuntime("top.theillusivec4.caelus:caelus-forge:3.2.0+1.20.1")
    modLocalRuntime("top.theillusivec4.curios:curios-forge:5.14.1+1.20.1")
    modLocalRuntime("vazkii.patchouli:Patchouli:1.20.1-80-FORGE")
    modImplementation("maven.modrinth:enigmatic-legacy:${mod.prop("forge.enigmatic_legacy")}")
    // Supplementaries
    modLocalRuntime("maven.modrinth:moonlight:zFdn1sMr")
    modImplementation("maven.modrinth:supplementaries:${mod.prop("forge.supplementaries")}")
    // Placebo
    modImplementation("curse.maven:placebo-283644:${mod.prop("forge.placebo")}")

    shadowBundle("blue.endless:jankson:1.2.3")
    forgeRuntimeLibrary("blue.endless:jankson:1.2.3")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowBundle(project(path = ":common", configuration = "transformProductionForge"))
}

tasks {
    processResources {
        inputs.property("version", mod.version)

        filesMatching("META-INF/mods.toml") {
            expand("version" to mod.version)
        }
        from(rootProject.file("assets/icon.png")) {
            rename { "${mod.id}-icon.png" }
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
