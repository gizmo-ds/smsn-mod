@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    id("com.github.johnrengelman.shadow")
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

val common: Configuration by configurations.creating
val shadowBundle: Configuration by configurations.creating
val developmentNeoForge: Configuration by configurations.getting

configurations {
    compileOnly.configure { extendsFrom(common) }
    runtimeOnly.configure { extendsFrom(common) }
    developmentNeoForge.extendsFrom(common)

    shadowBundle.isCanBeResolved = true
    shadowBundle.isCanBeConsumed = false
}

repositories {
    maven {
        name = "NeoForged"
        url = uri("https://maven.neoforged.net/releases")
    }
    maven {
        name = "Aether Team"
        url = uri("https://packages.aether-mod.net/Nitrogen")
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
    maven {
        name = "Registrate"
        url = uri("https://mvn.devos.one/snapshots")
        content {
            includeGroup("com.tterrag.registrate")
        }
    }
    maven {
        name = "Ponder"
        url = uri("https://maven.createmod.net/")
        content {
            includeGroup("net.createmod.ponder")
            includeGroup("dev.engine-room.flywheel")
        }
    }
}

dependencies {
    neoForge("net.neoforged:neoforge:${mod.prop("neoforge.version")}")

    modApi("me.shedaniel.cloth:cloth-config-neoforge:${mod.prop("cloth_config")}")

    // Supplementaries
    modLocalRuntime("maven.modrinth:moonlight:kC4rX7ac")
    modImplementation("mods:supplementaries:${mod.prop("neoforge.supplementaries")}")
    // Petrolpark's Library
    modLocalRuntime("com.tterrag.registrate:Registrate:MC1.21-1.3.0+62")
    modLocalRuntime("net.createmod.ponder:Ponder-NeoForge-1.21.1:1.0.45")
    modImplementation("maven.modrinth:petrolpark:${mod.prop("neoforge.petrolpark")}")
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly("maven.modrinth:inventory-profiles-next:${mod.prop("neoforge.ipn")}")
    // Blueprint
    modImplementation("com.teamabnormals:blueprint:${mod.prop("neoforge.blueprint")}")
    // Aether Nitrogen
    modImplementation("com.aetherteam.nitrogen:nitrogen_internals:${mod.prop("neoforge.nitrogen")}")
    // Bagus lib
    modImplementation("maven.modrinth:bagus-lib:${mod.prop("neoforge.bagus_lib")}")
    // Immersive Engineering
    modLocalRuntime("malte0811:BlockModelSplitter:2.0.1")
    modLocalRuntime("malte0811:DualCodecs:0.1.2")
    modImplementation(
        group = "blusunrize.immersiveengineering", name = "ImmersiveEngineering",
        version = mod.prop("neoforge.immersive_engineering")
    )
    // Placebo
    modImplementation("curse.maven:placebo-283644:${mod.prop("neoforge.placebo")}")
    // Immersive Caves
    modImplementation("maven.modrinth:immersive-caves:${mod.prop("neoforge.immersive_caves")}")
    // Iris Shader
    modCompileOnly("maven.modrinth:iris:${mod.prop("neoforge.iris")}")
    // Actually Additions
    modImplementation("maven.modrinth:actually-additions:${mod.prop("neoforge.actually_additions")}")
    // Exposure
    modCompileOnly("maven.modrinth:exposure:${mod.prop("neoforge.exposure")}")
    // Titanium
    modCompileOnly("maven.modrinth:titanium:${mod.prop("neoforge.titanium")}")

    shadowBundle("blue.endless:jankson:1.2.3")
    forgeRuntimeLibrary("blue.endless:jankson:1.2.3")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowBundle(project(path = ":common", configuration = "transformProductionNeoForge"))
}

tasks {
    processResources {
        inputs.property("version", mod.version)

        filesMatching("META-INF/neoforge.mods.toml") {
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
