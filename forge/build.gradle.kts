@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    id("com.github.johnrengelman.shadow")
}

apply(plugin = "com.modrinth.minotaur")
apply(plugin = "net.darkhax.curseforgegradle")

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

    localRuntime("io.github.llamalad7:mixinextras-forge:0.4.1")

    modApi("me.shedaniel.cloth:cloth-config-forge:${mod.prop("cloth_config")}")

    // Quark
    modImplementation("maven.modrinth:quark:${mod.prop("forge.quark")}")
    // Alex's mods
    modImplementation("maven.modrinth:citadel:${mod.prop("forge.citadel")}")
    // Xaero's maps
    modImplementation("maven.modrinth:xaeros-minimap:${mod.prop("forge.xaeros_minimap")}")
    modImplementation("maven.modrinth:xaeros-world-map:${mod.prop("forge.xaeros_world_map")}")
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly("maven.modrinth:inventory-profiles-next:${mod.prop("forge.ipn")}")
    // Blueprint
    modImplementation("com.teamabnormals:blueprint:${mod.prop("forge.blueprint")}")
    // Bagus lib
    modImplementation("maven.modrinth:bagus-lib:${mod.prop("forge.bagus_lib")}")
    // Immersive Engineering
    modImplementation("maven.modrinth:immersiveengineering:${mod.prop("forge.immersive_engineering")}")
    // Enigmatic Legacy
//    modLocalRuntime("top.theillusivec4.caelus:caelus-forge:3.2.0+1.20.1")
//    modLocalRuntime("top.theillusivec4.curios:curios-forge:5.14.1+1.20.1")
    modLocalRuntime("vazkii.patchouli:Patchouli:${mod.prop("forge.patchouli")}")
    modImplementation("maven.modrinth:enigmatic-legacy:${mod.prop("forge.enigmatic_legacy")}")
    // Botania
    modImplementation("vazkii.botania:Botania:${mod.prop("forge.botania")}")
    // Titanium
    modCompileOnly("maven.modrinth:titanium:${mod.prop("forge.titanium")}")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowBundle(project(path = ":common", configuration = "transformProductionForge"))
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/mods.toml") {
            expand("version" to project.version)
        }
        from(rootProject.file("assets/icon.png")) {
            rename { "${mod.id}-icon.png" }
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
            mainFile.addEnvironment("Server", "Client")
        }
}