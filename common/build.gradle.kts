architectury {
    common(mod.enabled_platforms)
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${mod.prop("fabric.loader")}")

    modImplementation("me.shedaniel.cloth:cloth-config:${mod.prop("cloth_config")}")
}
