package dev.aika.smsn.forge.compat.cloth;

import dev.aika.smsn.compat.cloth.SMSNClothConfig;
import dev.aika.smsn.config.MissingClothConfigScreen;
import dev.aika.smsn.forge.SMSNPlatformImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class ClothConfigCompat {
    public static Screen setup(Minecraft ignoredMinecraft, Screen parent) {
        if (SMSNPlatformImpl.isModLoaded("cloth_config")) return SMSNClothConfig.ConfigScreen(parent);
        else return new MissingClothConfigScreen(parent);
    }
}
