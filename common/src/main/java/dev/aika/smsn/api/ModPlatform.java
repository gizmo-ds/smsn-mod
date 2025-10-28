package dev.aika.smsn.api;

import dev.architectury.injectables.annotations.ExpectPlatform;
import lombok.experimental.UtilityClass;

@SuppressWarnings("unused")
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
