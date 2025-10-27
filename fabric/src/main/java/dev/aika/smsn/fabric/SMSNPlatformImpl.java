package dev.aika.smsn.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
