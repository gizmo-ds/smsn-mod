package dev.aika.smsn.neoforge.compat;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.api.ModPlatform;
import dev.aika.smsn.client.gui.screen.MissingClothConfigScreen;
import dev.aika.smsn.compat.ClothConfigScreen;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;

public class ClothConfigCompat {
    public static Screen setup(ModContainer ignoredModContainer, Screen parent) {
        return ModPlatform.isModLoaded("cloth_config") ?
                ClothConfigScreen.builder()
                        .setParent(parent).setModId(SMSN.MOD_ID)
                        .setConfig(SMSN.CONFIG).setMixinManager(SMSN.MixinManager)
                        .build()
                : new MissingClothConfigScreen(parent);
    }
}
