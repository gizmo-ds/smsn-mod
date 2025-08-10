package dev.aika.smsn.fabric.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.aika.smsn.SMSN;
import dev.aika.smsn.fabric.SMSNPlatformImpl;
import dev.aika.smsn.client.gui.MissingClothConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClothConfigCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (SMSNPlatformImpl.isModLoaded("cloth-config2"))
                return dev.aika.smsn.compat.ClothConfigCompat.ConfigScreen(SMSN.CONFIG, parent);
            else return new MissingClothConfigScreen(parent);
        };
    }
}
