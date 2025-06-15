@file:Suppress("UnstableApiUsage")

plugins {
    id("com.github.johnrengelman.shadow")
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
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${mod.prop("fabric.loader")}")

    modApi("me.shedaniel.cloth:cloth-config-fabric:${mod.prop("cloth_config")}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modImplementation("com.terraformersmc:modmenu:11.0.3")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${mod.prop("fabric.api")}")

    // Supplementaries
    modLocalRuntime("maven.modrinth:moonlight:WGUxPaDX")
    modImplementation("maven.modrinth:supplementaries:${mod.prop("fabric.supplementaries")}")
    // Inventory Profiles Next (I can't make this work. ¯\_(ツ)_/¯)
    modCompileOnly("maven.modrinth:inventory-profiles-next:${mod.prop("fabric.ipn")}")

    shadowBundle("blue.endless:jankson:1.2.3")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowBundle(project(path = ":common", configuration = "transformProductionFabric"))
}

tasks {
    processResources {
        inputs.property("version", mod.version)

        filesMatching("fabric.mod.json") {
            expand("version" to mod.version)
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
