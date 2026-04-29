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

    @ExpectPlatform
    public static ModInfo getModInfo(String modId) {
        throw new AssertionError();
    }

    public record ModInfo(String modId, String name, String version) {
    }

    @ExpectPlatform
    public static boolean containsVersion(String version, String versionRange) {
        throw new AssertionError();
    }
}
