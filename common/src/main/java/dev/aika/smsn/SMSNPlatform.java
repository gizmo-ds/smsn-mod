package dev.aika.smsn;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class SMSNPlatform {
    @ExpectPlatform
    public static boolean isModLoaded(String modId) {
        throw new AssertionError();
    }
}
