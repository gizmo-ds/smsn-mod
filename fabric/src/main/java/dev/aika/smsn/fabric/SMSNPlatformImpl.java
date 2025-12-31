package dev.aika.smsn.fabric;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.config.ModMenuConfigManager;
import dev.aika.smsn.api.ModPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static boolean isModVersionCheckEnabled() {
        return ModPlatform.isModLoaded("modmenu") && ModMenuConfig.UPDATE_CHECKER.getValue();
    }

    public static void setModVersionCheckEnabled(boolean enabled) {
        if (ModPlatform.isModLoaded("modmenu")) {
            ModMenuConfig.UPDATE_CHECKER.setValue(enabled);
            ModMenuConfigManager.save();
        }
    }
}
