@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URI
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

plugins {
    alias(libs.plugins.shadow)
}

apply(plugin = "com.modrinth.minotaur")
apply(plugin = "net.darkhax.curseforgegradle")

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
    maven("https://maven.neoforged.net/releases") { name = "NeoForged" }
    maven("https://maven.teamabnormals.com") { name = "Blueprint" }
    maven("https://maven.blamejared.com/") {
        name = "Immersive Engineering"
        content { includeGroup("blusunrize.immersiveengineering") }
    }
    maven("https://mvn.devos.one/snapshots") {
        name = "Registrate"
        content { includeGroup("com.tterrag.registrate") }
    }
    maven("https://maven.createmod.net/") {
        name = "Ponder"
        content {
            includeGroup("net.createmod.ponder")
            includeGroup("dev.engine-room.flywheel")
        }
    }
    maven("https://maven.shadowsoffire.dev/releases") {
        content { includeGroup("dev.shadowsoffire") }
    }
}

dependencies {
    neoForge(libs.neoforge.neoforge)

    localRuntime(libs.mixinextras.neoforge)
    modLocalRuntime(libs.neoforge.norealmsbutton)

    modApi(libs.clothconfig.neoforge)
//    modCompileOnly(libs.clothconfig.neoforge)

    // Supplementaries
    modLocalRuntime(libs.neoforge.moonlight)
    modImplementation(downloadAndPatchJar(libs.neoforge.supplementaries.get()))
    // Petrolpark's Library
    modLocalRuntime(libs.neoforge.registrate)
    modLocalRuntime(libs.neoforge.ponder)
    modImplementation(libs.neoforge.petrolpark)
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly(libs.neoforge.ipn)
    // Blueprint
    modImplementation(libs.neoforge.blueprint)
    // Aether Nitrogen
    modImplementation(libs.neoforge.nitrogen)
    // Bagus lib
    modImplementation(libs.neoforge.baguslib)
    // Immersive Engineering
    modCompileOnly(libs.neoforge.immersiveengineering)
    // Placebo
    modImplementation(libs.neoforge.placebo)
    // Immersive Caves
    modImplementation(libs.neoforge.immersivecaves)
    // Iris Shader
    modCompileOnly(libs.neoforge.iris)
    // Actually Additions
    modImplementation(libs.neoforge.actuallyadditions)
    // Exposure
    modImplementation(libs.neoforge.exposure)
    // Titanium
    modImplementation(libs.neoforge.titanium)
    // Ribbits
    modCompileOnly(libs.neoforge.ribbits)
    // Xaero's Maps
    modImplementation(libs.neoforge.xaeros.minimap)
    modImplementation(libs.neoforge.xaeros.worldmap)
    // M.R.U
    modImplementation(libs.neoforge.mru)

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowBundle(project(path = ":common", configuration = "transformProductionNeoForge"))
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/neoforge.mods.toml") {
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

fun downloadAndPatchJar(dep: MinimalExternalModuleDependency): String {
    val modFile = file("./mods/${dep.name}-${dep.version}.jar")
    modFile.parentFile.mkdirs()
    if (modFile.exists()) return "mods:${dep.name}:${dep.version}"

    val tempFile = File.createTempFile("temp", ".jar")
    tempFile.deleteOnExit()

    println("Downloading ${dep.name}...")
    URI("https://api.modrinth.com/maven/maven/modrinth/${dep.name}/${dep.version}/${dep.name}-${dep.version}.jar")
        .toURL().openStream().use {
            it.copyTo(BufferedOutputStream(tempFile.outputStream()))
        }

    println("Patching ${dep.name}...")
    FileInputStream(tempFile).use { it ->
        ZipInputStream(it).use { zis ->
            FileOutputStream(modFile).use {
                ZipOutputStream(it).use { zos ->
                    var entry = zis.nextEntry
                    while (entry != null) {
                        if (entry.name != "architectury.common.json") {
                            zos.putNextEntry(ZipEntry(entry.name))
                            if (!entry.isDirectory) zis.copyTo(zos)
                            zos.closeEntry()
                        }
                        entry = zis.nextEntry
                    }
                }
            }
        }
    }
    return "mods:${dep.name}:${dep.version}"
}
