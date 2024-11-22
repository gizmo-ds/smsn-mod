package dev.aika.smsn.config;

import dev.aika.smsn.SMSN;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ClothConfigCommonImpl {
    public static void loadConfig() {
        var holder = AutoConfig.register(ModConfigData.class, JanksonConfigSerializer::new);
        SMSN.CONFIG = holder.getConfig();

        AutoConfig.getGuiRegistry(ModConfigData.class);
    }
}
