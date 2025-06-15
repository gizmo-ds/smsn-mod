@file:Suppress("PropertyName")

import org.gradle.api.Project

val Project.mod: ModData get() = ModData(this)

@JvmInline
value class ModData(private val project: Project) {
    private fun strProp(key: String): String =
        requireNotNull(project.findProperty(key)?.toString()) { "Property $key not set." }

    val id: String get() = strProp("mod.id")
    val version: String get() = strProp("mod.version")
    val group: String get() = strProp("mod.group")
    val minecraft_version: String get() = strProp("minecraft_version")
    val enabled_platforms: List<String> get() = strProp("mod.enabled_platforms").split(",")

    fun prop(key: String) = strProp(key)
}
