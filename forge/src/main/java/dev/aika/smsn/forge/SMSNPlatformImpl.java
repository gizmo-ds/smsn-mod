package dev.aika.smsn.forge;

import net.minecraftforge.fml.loading.FMLConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isModVersionCheckEnabled() {
        return FMLConfig.getBoolConfigValue(FMLConfig.ConfigValue.VERSION_CHECK);
    }

    public static void setModVersionCheckEnabled(boolean enabled) {
        FMLConfig.updateConfig(FMLConfig.ConfigValue.VERSION_CHECK, enabled);
    }
}
