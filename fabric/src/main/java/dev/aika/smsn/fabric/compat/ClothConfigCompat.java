package dev.aika.smsn.fabric.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.aika.smsn.SMSN;
import dev.aika.smsn.api.ModPlatform;
import dev.aika.smsn.client.gui.screen.MissingClothConfigScreen;
import dev.aika.smsn.compat.ClothConfigScreen;

public class ClothConfigCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent ->
                ModPlatform.isModLoaded("cloth-config2") ?
                        ClothConfigScreen.builder()
                                .setParent(parent).setModId(SMSN.MOD_ID)
                                .setConfig(SMSN.CONFIG).setMixinManager(SMSN.MixinManager)
                                .build()
                        : new MissingClothConfigScreen(parent);
    }
}
