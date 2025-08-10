@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    alias(libs.plugins.shadow)
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

repositories {
    maven("https://maven.theillusivec4.top/") { name = "Curios" }
    maven("https://maven.teamabnormals.com") { name = "Blueprint" }
    maven("https://maven.blamejared.com/") {
        name = "Immersive Engineering"
        content { includeGroup("blusunrize.immersiveengineering") }
    }
}

dependencies {
    forge(libs.forge.forge)

    localRuntime(libs.mixinextras.forge)
    modLocalRuntime(libs.lazydfu.forge)

    modApi(libs.clothconfig.forge)
//    modCompileOnly(libs.clothconfig.forge)

    // Quark
    modImplementation(libs.forge.quark)
    // Alex's mods
    modImplementation(libs.forge.citadel)
    // Xaero's maps
    modImplementation(libs.forge.xaeros.minimap)
    modImplementation(libs.forge.xaeros.worldmap)
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly(libs.forge.ipn)
    // Blueprint
    modImplementation(libs.forge.blueprint)
    // Bagus lib
    modImplementation(libs.forge.baguslib)
    // Immersive Engineering
    modImplementation(libs.forge.immersiveengineering)
    // Enigmatic Legacy
//    modLocalRuntime("top.theillusivec4.caelus:caelus-forge:3.2.0+1.20.1")
//    modLocalRuntime("top.theillusivec4.curios:curios-forge:5.14.1+1.20.1")
    modLocalRuntime(libs.forge.patchouli)
    modImplementation(libs.forge.enigmaticlegacy)
    // Botania
    modImplementation(libs.forge.botania)
    // Titanium
    modImplementation(libs.forge.titanium)

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
        }
}