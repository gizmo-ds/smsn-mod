@file:Suppress("PropertyName")

import org.gradle.api.Project

val Project.mod: ModData get() = ModData(this)

@JvmInline
value class ModData(private val project: Project) {
    fun prop(key: String): String = requireNotNull(project.findProperty(key)?.toString()) { "Property $key not set." }

    val id: String get() = prop("mod.id")
    val name: String get() = prop("mod.name")
    val version: String get() = prop("mod.version")
    val group: String get() = prop("mod.group")
    val minecraft_version: String get() = prop("minecraft_version")
    val mc_version: String get() = minecraft_version
    val enabled_platforms: List<String> get() = prop("mod.enabled_platforms").split(",")
}
