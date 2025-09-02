package dev.aika.smsn.mixin;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class MixinPlatform {
    @ExpectPlatform
    public static void register() {
        throw new AssertionError();
    }
}
