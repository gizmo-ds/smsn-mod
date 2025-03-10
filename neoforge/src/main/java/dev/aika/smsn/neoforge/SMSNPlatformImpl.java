package dev.aika.smsn.neoforge;

import net.neoforged.fml.loading.FMLLoader;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }
}
