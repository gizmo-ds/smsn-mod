package dev.aika.smsn.mixin;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.api.ModPlatform;
import lombok.Getter;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModMixinInfo implements Comparable<ModMixinInfo> {
    @Getter
    private final String modId;
    @Getter
    private final List<String> mixinClasses;
    @Getter
    private final boolean isModLoaded;

    public ModMixinInfo(String modId, List<String> mixinClasses) {
        this.modId = modId;
        this.mixinClasses = mixinClasses;
        this.isModLoaded = ModPlatform.isModLoaded(modId);
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
        return isModLoaded && !SMSN.CONFIG.disabledMixins.contains(mixinClassName);
    }
}
