package dev.aika.smsn.fabric;

import net.fabricmc.loader.api.FabricLoader;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}
