package dev.aika.smsn.forge.compat;

import dev.aika.smsn.client.gui.MissingClothConfigScreen;
import dev.aika.smsn.compat.ClothConfigScreen;
import dev.aika.smsn.forge.SMSNPlatformImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class ClothConfigCompat {
    public static Screen setup(Minecraft ignoredMinecraft, Screen parent) {
        if (SMSNPlatformImpl.isModLoaded("cloth_config")) return ClothConfigScreen.create(parent);
        else return new MissingClothConfigScreen(parent);
    }
}
