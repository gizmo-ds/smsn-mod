package dev.aika.smsn;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;
import java.util.Map;

public class SMSNPlatform {
    @ExpectPlatform
    public static boolean isModLoaded(String modId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Map<String, String> getMixinClassNames() {
        throw new AssertionError();
    }
}
