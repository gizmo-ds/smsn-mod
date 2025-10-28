@file:Suppress("PropertyName")

import org.gradle.api.Project

val Project.mod: ModData get() = ModData(this)
val Project.pub: PubData get() = PubData(this)

@JvmInline
value class ModData(private val project: Project) {
    fun prop(key: String): String = requireNotNull(project.findProperty(key)?.toString()) { "Property $key not set." }

    val id: String get() = prop("mod.id")
    val name: String get() = prop("mod.name")
    val version: String get() = prop("mod.version")
    val group: String get() = prop("mod.group")
    val release_type: String get() = prop("mod.release_type")
    val minecraft_version: String get() = prop("minecraft_version")
    val enabled_platforms: List<String> get() = prop("mod.enabled_platforms").split(",")
}

@JvmInline
value class PubData(private val project: Project) {
    fun prop(key: String): String = requireNotNull(project.findProperty(key)?.toString()) { "Property $key not set." }

    val modrinth_id: String get() = prop("pub.modrinth_id")
    val curseforge_id: String get() = prop("pub.curseforge_id")
    val debug: Boolean get() = prop("pub.debug").toBoolean()
    val enabled_platforms: List<String> get() = prop("pub.enabled_platforms").split(",")
    val game_version_supports: List<String> get() = prop("pub.game_version_supports").split(",")
}
