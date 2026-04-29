package dev.aika.smsn.api.forge;

import dev.aika.smsn.api.ModPlatform;
import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;

@SuppressWarnings("unused")
@UtilityClass
public class ModPlatformImpl {
    public static boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

    public static String getModName(String modId) {
        if (!isModLoaded(modId)) return null;
        return FMLLoader.getLoadingModList().getModFileById(modId).moduleName();
    }

    public static ModPlatform.ModInfo getModInfo(String modId) {
        final var info = FMLLoader.getLoadingModList().getModFileById(modId);
        if (info == null) return null;
        return new ModPlatform.ModInfo(modId, info.moduleName(), info.versionString());
    }

    public static boolean containsVersion(String version, String versionRange) {
        try {
            return VersionRange.createFromVersionSpec(versionRange).containsVersion(new DefaultArtifactVersion(version));
        } catch (InvalidVersionSpecificationException e) {
            return false;
        }
    }
}
