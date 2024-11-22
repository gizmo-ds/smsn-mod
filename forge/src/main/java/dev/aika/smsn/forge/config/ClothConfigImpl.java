package dev.aika.smsn.forge.config;

import dev.aika.smsn.config.ClothConfigCommonImpl;
import dev.aika.smsn.config.MissingClothConfigScreen;
import dev.aika.smsn.config.ModConfigData;
import dev.aika.smsn.forge.SMSNPlatformImpl;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public class ClothConfigImpl {
    private static boolean clothConfigIsInstalled() {
        return SMSNPlatformImpl.isModLoaded("cloth_config");
    }

    public static void registerConfigGui() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> {
                            if (clothConfigIsInstalled())
                                return AutoConfig.getConfigScreen(ModConfigData.class, parent).get();
                            else return new MissingClothConfigScreen(parent);
                        }));
    }

    public static void loadConfig() {
        if (clothConfigIsInstalled()) ClothConfigCommonImpl.loadConfig();
    }
}
