package dev.aika.smsn.fabric.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.aika.smsn.gui.MissingClothConfigScreen;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (ClothConfigImpl.clothConfigIsInstalled())
                return AutoConfig.getConfigScreen(ModConfigDataFabric.class, parent).get();
            else return new MissingClothConfigScreen(parent);
        };
    }
}
