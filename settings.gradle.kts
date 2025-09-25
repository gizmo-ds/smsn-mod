pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://files.minecraftforge.net/maven/")
        maven("https://maven.firstdark.dev/releases") {
            content {
                includeGroup("com.hypherionmc.modutils")
                includeGroup("com.hypherionmc.modutils.modpublisher")
            }
        }
        gradlePluginPortal()
    }
}

rootProject.name = "smsn"

include("common", "fabric", "forge")
