package dev.aika.smsn.fabric.config;

import dev.aika.smsn.config.ClothConfigCommonImpl;
import dev.aika.smsn.fabric.SMSNPlatformImpl;

public class ClothConfigImpl {
    public static boolean clothConfigIsInstalled() {
        return SMSNPlatformImpl.isModLoaded("cloth-config2");
    }

    public static void loadConfig() {
        if (clothConfigIsInstalled()) ClothConfigCommonImpl.loadConfig();
    }
}
