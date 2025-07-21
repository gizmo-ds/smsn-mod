@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

plugins {
    id("com.github.johnrengelman.shadow")
    id("com.modrinth.minotaur").version("2.+")
    id("net.darkhax.curseforgegradle").version("1.+")
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val common: Configuration by configurations.creating
val shadowBundle: Configuration by configurations.creating
val developmentFabric: Configuration by configurations.getting

configurations {
    compileOnly.configure { extendsFrom(common) }
    runtimeOnly.configure { extendsFrom(common) }
    developmentFabric.extendsFrom(common)

    shadowBundle.isCanBeResolved = true
    shadowBundle.isCanBeConsumed = false
}

repositories {
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/")
    }
    maven {
        name = "trinkets"
        url = uri("https://maven.ladysnake.org/releases")
    }
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${mod.prop("fabric.loader")}")

    implementation(include("io.github.llamalad7:mixinextras-fabric:0.4.1")!!)

    modApi("me.shedaniel.cloth:cloth-config-fabric:${mod.prop("cloth_config")}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modImplementation("com.terraformersmc:modmenu:7.2.2")

    modLocalRuntime("net.fabricmc.fabric-api:fabric-api:${mod.prop("fabric.api")}")

    // Xaero's maps
    modImplementation("maven.modrinth:xaeros-minimap:${mod.prop("fabric.xaeros_minimap")}")
    modImplementation("maven.modrinth:xaeros-world-map:${mod.prop("fabric.xaeros_world_map")}")
    // Supplementaries
    modCompileOnly("maven.modrinth:moonlight:dOeX0aw2")
    modCompileOnly("maven.modrinth:supplementaries:${mod.prop("fabric.supplementaries")}")
    // Botania
    modImplementation("vazkii.botania:Botania:${mod.prop("fabric.botania")}") {
        exclude(group = "com.jamieswhiteshirt")
    }
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly("maven.modrinth:inventory-profiles-next:${mod.prop("fabric.ipn")}")
    // Iris
    modLocalRuntime("maven.modrinth:sodium:vgceLbdH")
    modImplementation("maven.modrinth:iris:${mod.prop("fabric.iris")}")
    // Ad Astra!
    modCompileOnly("maven.modrinth:ad-astra:${mod.prop("fabric.ad_astra")}")
    // Exposure
    modCompileOnly("maven.modrinth:exposure:${mod.prop("fabric.exposure")}")
    // Ribbits
    modCompileOnly("maven.modrinth:ribbits:${mod.prop("fabric.ribbits")}")
    // M.R.U
    modImplementation("maven.modrinth:mru:${mod.prop("fabric.mru")}")

    shadowBundle("blue.endless:jankson:1.2.3")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowBundle(project(path = ":common", configuration = "transformProductionFabric"))
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
        from(rootProject.file("assets/icon.png")) {
            rename { "assets/${mod.id}/icon.png" }
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

tasks.register<net.darkhax.curseforgegradle.TaskPublishCurseForge>("curseforge") {
    group = "publishing"

    val curseforgeToken: String = env.fetch("CF_TOKEN", "").trim()
    val curseforgeId: String = mod.prop("curseforge_id")
    if (curseforgeId.isEmpty() || curseforgeToken.isEmpty()) {
        isEnabled = false
        return@register
    }

    apiToken = curseforgeToken
    val mainFile = upload(curseforgeId, tasks.remapJar.flatMap { it.archiveFile })
    mainFile.releaseType = mod.release_type
    mainFile.gameVersions.addAll(mod.game_version_supports)
    mainFile.addModLoader(loom.platform.get().displayName())
    mainFile.addOptional("cloth-config")
    mainFile.changelog = ""
}

val modrinthToken: String = env.fetch("MODRINTH_TOKEN", "").trim()
val modrinthId: String = mod.prop("modrinth_id")
if (modrinthId.isNotEmpty() && modrinthToken.isNotEmpty()) {
    modrinth {
        token.set(modrinthToken)
        projectId.set(modrinthId)

        val readme = rootProject.file("README.md").readText()
        if (readme.isNotEmpty()) syncBodyFrom.set(readme)

        versionName.set("${mod.version} ${loom.platform.get().displayName()}")
        versionNumber.set(project.version.toString())
        versionType.set(mod.release_type)
        uploadFile.set(tasks.remapJar.flatMap { it.archiveFile })
        gameVersions.addAll(mod.game_version_supports)
        loaders.add(loom.platform.get().id())
        dependencies {
            optional.project("cloth-config")
        }
    }
}
