package dev.aika.smsn.neoforge.compat;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.client.gui.MissingClothConfigScreen;
import dev.aika.smsn.neoforge.SMSNPlatformImpl;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;

public class ClothConfigCompat {
    public static Screen setup(ModContainer ignoredModContainer, Screen parent) {
        if (SMSNPlatformImpl.isModLoaded("cloth_config"))
            return dev.aika.smsn.compat.ClothConfigCompat.ConfigScreen(SMSN.CONFIG, parent);
        else return new MissingClothConfigScreen(parent);
    }
}
