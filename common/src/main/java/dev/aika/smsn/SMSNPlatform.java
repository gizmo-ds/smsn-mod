package dev.aika.smsn;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

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
    public static String getPlatform() {
        throw new AssertionError();
    }
}
