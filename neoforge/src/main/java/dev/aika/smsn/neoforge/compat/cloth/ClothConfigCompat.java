package dev.aika.smsn.neoforge.compat.cloth;

import dev.aika.smsn.compat.cloth.SMSNClothConfig;
import dev.aika.smsn.gui.MissingClothConfigScreen;
import dev.aika.smsn.neoforge.SMSNPlatformImpl;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;

public class ClothConfigCompat {
    public static Screen setup(ModContainer ignoredModContainer, Screen parent) {
        if (SMSNPlatformImpl.isModLoaded("cloth_config")) return SMSNClothConfig.ConfigScreen(parent);
        else return new MissingClothConfigScreen(parent);
    }
}
