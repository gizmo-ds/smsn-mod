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

apply(plugin = "com.hypherionmc.modutils.modpublisher")

architectury { neoForge() }

val shadowBundle: Configuration by configurations.getting
val developmentNeoForge: Configuration by configurations.getting
configurations {
    developmentNeoForge.extendsFrom(common.get())
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
    maven("https://maven.blamejared.com") {
        name = "vazkii"
        content {
            includeGroup("org.violetmoon.quark")
        }
    }
}

dependencies {
    neoForge(libs.neoforge.neoforge)

    localRuntime(libs.mixinextras.neoforge)
    modLocalRuntime(libs.neoforge.norealmsbutton)

    modApi(libs.clothconfig.neoforge)
//    modCompileOnly(libs.clothconfig.neoforge)

    // Supplementaries
    modCompileOnly(downloadAndPatchJar(libs.neoforge.supplementaries.get()))
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
    // ProjectE
    modImplementation(libs.neoforge.projecte)
    // Hexerei
    modCompileOnly(libs.neoforge.hexerei)
    // AdditionalStructures
    modImplementation(libs.neoforge.additionalstructures)
    // TenshiLib
    modImplementation(libs.neoforge.tenshilib)
    // Ars Nouveau
    modCompileOnly(libs.neoforge.arsnouveau)
    // Immersive Portals
    modCompileOnly(libs.fabric.immersiveportals)
    // Quark
    modCompileOnly(libs.neoforge.quark)
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/neoforge.mods.toml") { expand("version" to project.version) }
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
