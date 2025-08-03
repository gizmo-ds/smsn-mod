package dev.aika.smsn.fabric;

import dev.aika.smsn.fabric.mixin.MixinInfo;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.Map;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static Map<String, String> getMixinClassNames() {
        return MixinInfo.getMixinClassNames();
    }
}
