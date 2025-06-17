@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    id("com.github.johnrengelman.shadow")
    id("me.shedaniel.unified-publishing") version "0.1.+"
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
    maven {
        name = "Fuzss"
        url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
        content {
            includeGroup("fuzs.forgeconfigapiport")
        }
    }
}

dependencies {
    neoForge("net.neoforged:neoforge:${mod.prop("neoforge.version")}")

    modApi("me.shedaniel.cloth:cloth-config-neoforge:${mod.prop("cloth_config")}")

    // Supplementaries
    modLocalRuntime("maven.modrinth:moonlight:kC4rX7ac")
    modImplementation("libs:supplementaries:${mod.prop("neoforge.supplementaries")}")
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
    modImplementation("blusunrize.immersiveengineering:ImmersiveEngineering:${mod.prop("neoforge.immersive_engineering")}")
    // Placebo
    modImplementation("curse.maven:placebo-283644:${mod.prop("neoforge.placebo")}")
    // Immersive Caves
    modImplementation("maven.modrinth:immersive-caves:${mod.prop("neoforge.immersive_caves")}")
    // Iris Shader
    modCompileOnly("maven.modrinth:iris:${mod.prop("neoforge.iris")}")
    // Actually Additions
    modImplementation("maven.modrinth:actually-additions:${mod.prop("neoforge.actually_additions")}")

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

unifiedPublishing {
    project {
        version.set("${mod.version}+mc${mod.minecraft_version}")
        displayName.set("${mod.name} v${mod.version}")
        gameVersions.add(mod.minecraft_version)
        gameLoaders.set(listOf("neoforge"))
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