package dev.aika.smsn.fabric.compat.cloth;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.aika.smsn.compat.cloth.SMSNClothConfig;
import dev.aika.smsn.config.MissingClothConfigScreen;
import dev.aika.smsn.fabric.SMSNPlatformImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClothConfigCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (SMSNPlatformImpl.isModLoaded("cloth-config2")) return SMSNClothConfig.ConfigScreen(parent);
            else return new MissingClothConfigScreen(parent);
        };
    }
}
