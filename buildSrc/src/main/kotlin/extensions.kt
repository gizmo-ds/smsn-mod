@file:Suppress("PropertyName")

import org.gradle.api.Project

val Project.mod: ModData get() = ModData(this)

@JvmInline
value class ModData(private val project: Project) {
    private fun prop(key: String): String =
        requireNotNull(project.findProperty(key)?.toString()) { "Property $key not set." }

    val id: String get() = prop("mod.id")
    val name: String get() = prop("mod.name")
    val version: String get() = prop("mod.version")
    val group: String get() = prop("mod.group")
    val release_type: String get() = prop("mod.release_type")
    val minecraft_version: String get() = prop("minecraft_version")
    val game_version_supports: List<String> get() = prop("mod.game_version_supports").split(",")
    val enabled_platforms: List<String> get() = prop("mod.enabled_platforms").split(",")

    val modrinth_id: String get() = prop("modrinth_id")
    val curseforge_id: String get() = prop("curseforge_id")
    val debug_publishing: Boolean get() = prop("debug_publishing") == "true"
    val publish_platforms: List<String> get() = prop("publish_platforms").split(",")
}
