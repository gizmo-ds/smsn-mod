package dev.aika.smsn.api;

import dev.architectury.injectables.annotations.ExpectPlatform;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ModPlatform {
    @ExpectPlatform
    public boolean isModLoaded(String modId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public String getModName(String modId) {
        throw new AssertionError();
    }
}
