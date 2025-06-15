package dev.aika.smsn.neoforge;

import dev.aika.smsn.neoforge.mixin.MixinInfo;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.Map;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static Map<String, String> getMixinClassNames() {
        return MixinInfo.getMixinClassNames();
    }
}
