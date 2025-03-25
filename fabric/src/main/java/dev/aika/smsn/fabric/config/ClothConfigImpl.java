package dev.aika.smsn.fabric.config;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.fabric.SMSNPlatformImpl;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ClothConfigImpl {
    public static boolean clothConfigIsInstalled() {
        return SMSNPlatformImpl.isModLoaded("cloth-config2");
    }

    public static void loadConfig() {
        if (clothConfigIsInstalled()) {
            var holder = AutoConfig.register(ModConfigDataFabric.class, JanksonConfigSerializer::new);
            SMSN.CONFIG = holder.getConfig();
            AutoConfig.getGuiRegistry(ModConfigDataFabric.class);
        }
    }
}
