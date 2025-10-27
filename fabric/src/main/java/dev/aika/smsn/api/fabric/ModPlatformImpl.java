package dev.aika.smsn.api.fabric;

import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;

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
}
