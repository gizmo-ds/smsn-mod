package dev.aika.smsn.api.forge;

import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.loading.FMLLoader;

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
