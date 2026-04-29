package dev.aika.smsn.mixin;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.api.ModPlatform;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Accessors(fluent = true)
public class ModMixinInfo implements Comparable<ModMixinInfo> {
    @Getter
    private final String modId;
    @Getter
    private final List<String> mixinClasses;
    @Getter
    private final boolean isModLoaded;
    @Getter
    private final String version;
    @Getter
    @Setter
    private String versionRange;

    public ModMixinInfo(String modId, List<String> mixinClasses, String versionRange) {
        this.modId = modId;
        this.mixinClasses = mixinClasses;
        this.isModLoaded = ModPlatform.isModLoaded(modId);
        this.version = isModLoaded ? ModPlatform.getModInfo(modId).version() : null;
        this.versionRange = versionRange;
    }

    public ModMixinInfo(String modId, List<String> mixinClasses) {
        this(modId, mixinClasses, null);
    }

    public ModMixinInfo(String modId, @Language(value = "JAVA", suffix = ".class") String... mixinClasses) {
        this(modId, List.of(mixinClasses));
    }

    @Override
    public int compareTo(@NotNull ModMixinInfo o) {
        if (modId.hashCode() < o.modId.hashCode())
            return -1;
        else if (modId.hashCode() == o.modId.hashCode())
            return 0;
        return 1;
    }

    public boolean shouldApply(String mixinClassName) {
        if (!isModLoaded) return false;
        if (SMSN.CONFIG.disabledMixins.contains(mixinClassName)) return false;
        if (version == null) return false;
        if (versionRange != null) return ModPlatform.containsVersion(version, versionRange);
        return true;
    }
}
