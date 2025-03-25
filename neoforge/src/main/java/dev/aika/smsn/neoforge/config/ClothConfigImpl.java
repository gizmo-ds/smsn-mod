package dev.aika.smsn.neoforge.config;

import dev.aika.smsn.gui.MissingClothConfigScreen;
import dev.aika.smsn.neoforge.SMSNPlatformImpl;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;

public class ClothConfigImpl {
    private static boolean clothConfigIsInstalled() {
        return SMSNPlatformImpl.isModLoaded("cloth_config");
    }

    public static Screen setup(ModContainer ignoredModContainer, Screen parent) {
        if (clothConfigIsInstalled())
            return AutoConfig.getConfigScreen(ModConfigDataNeoForge.class, parent).get();
        else return new MissingClothConfigScreen(parent);
    }
}
