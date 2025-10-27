package dev.aika.smsn.neoforge;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class SMSNPlatformImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }
}
