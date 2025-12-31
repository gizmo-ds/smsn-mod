package dev.aika.smsn;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class SMSNPlatform {
    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModVersionCheckEnabled() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setModVersionCheckEnabled(boolean enabled) {
        throw new AssertionError();
    }
}
