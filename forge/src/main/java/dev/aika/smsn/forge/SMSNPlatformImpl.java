package dev.aika.smsn.forge;

import net.minecraftforge.fml.loading.FMLLoader;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }
}
