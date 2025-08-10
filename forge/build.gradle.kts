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
    forge(libs.forge.version)

    localRuntime(libs.mixinextras.forge)

    modApi(libs.clothconfig.forge)
//    modCompileOnly(libs.clothconfig.forge)

    // Quark
    modLocalRuntime(libs.forge.autoreglib)
    modImplementation(libs.forge.quark)
    // Alex's mods
    modImplementation(libs.forge.citadel)
    // Xaero's maps
    modImplementation(libs.forge.xaeros.minimap)
    modImplementation(libs.forge.xaeros.worldmap)
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
    // Titanium
    modImplementation(libs.forge.titanium)
    // Botania
    modImplementation(libs.forge.botania)
    // ProjectE
    modImplementation(libs.forge.projecte)

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