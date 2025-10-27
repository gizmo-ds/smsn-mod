package dev.aika.smsn.api.neoforge;

import lombok.experimental.UtilityClass;
import net.neoforged.fml.loading.FMLLoader;

@SuppressWarnings("unused")
@UtilityClass
public class ModPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

    public static String getModName(String modId) {
        if (!isModLoaded(modId)) return null;
        return FMLLoader.getLoadingModList().getModFileById(modId).moduleName();
    }
}
