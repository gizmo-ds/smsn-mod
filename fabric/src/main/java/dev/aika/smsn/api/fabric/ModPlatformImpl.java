package dev.aika.smsn.api.fabric;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.api.ModPlatform;
import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.impl.util.version.VersionPredicateParser;

@SuppressWarnings("unused")
@UtilityClass
public class ModPlatformImpl {
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    public String getModName(String modId) {
        if (!isModLoaded(modId)) return null;
        return FabricLoader.getInstance().getModContainer(modId)
                .map(c -> c.getMetadata().getName())
                .orElse(modId);
    }

    public static ModPlatform.ModInfo getModInfo(String modId) {
        final var mcOpt = FabricLoader.getInstance().getModContainer(modId);
        if (mcOpt.isEmpty()) return null;
        var mc = mcOpt.get();
        return new ModPlatform.ModInfo(modId,
                mc.getMetadata().getName(),
                mc.getMetadata().getVersion().toString()
        );
    }

    public static boolean containsVersion(String version, String versionRange) {
        try {
            return VersionPredicateParser.parse(versionRange).test(Version.parse(version));
        } catch (VersionParsingException e) {
            SMSN.LOGGER.error(e.getMessage());
            return false;
        }
    }
}
