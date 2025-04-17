package dev.aika.smsn.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static String getPlatform() {
        return "fabric";
    }
}
