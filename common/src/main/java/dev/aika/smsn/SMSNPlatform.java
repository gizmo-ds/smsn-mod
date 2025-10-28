package dev.aika.smsn;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class SMSNPlatform {
    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }
}
