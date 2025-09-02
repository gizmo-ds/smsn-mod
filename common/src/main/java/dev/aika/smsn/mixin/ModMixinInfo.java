package dev.aika.smsn.mixin;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.SMSNPlatform;
import lombok.Getter;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModMixinInfo implements Comparable<ModMixinInfo> {
    @Getter
    private final String modid;
    @Getter
    private final List<String> mixinClasses;
    @Getter
    private final boolean isModLoaded;

    private ModMixinInfo(String modid, List<String> mixinClasses) {
        this.modid = modid;
        this.mixinClasses = mixinClasses;
        this.isModLoaded = SMSNPlatform.isModLoaded(modid);
    }

    public static ModMixinInfo create(String modid, @Language(value = "JAVA", suffix = ".class") String... mixinClasses) {
        return new ModMixinInfo(modid, List.of(mixinClasses));
    }

    @Override
    public int compareTo(@NotNull ModMixinInfo o) {
        if (modid.hashCode() < o.modid.hashCode())
            return -1;
        else if (modid.hashCode() == o.modid.hashCode())
            return 0;
        return 1;
    }

    public boolean shouldApply(String mixinClassName) {
        return isModLoaded && !SMSN.CONFIG.disabledMixins.contains(mixinClassName);
    }
}
