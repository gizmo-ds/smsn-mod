package dev.aika.smsn.mixin;

import java.util.*;

public class ModMixinManager {
    private static final ModMixinManager INSTANCE = new ModMixinManager();
    private final Set<ModMixinInfo> mixinInfos = new LinkedHashSet<>();

    private ModMixinManager() {
    }

    public static void addInfo(ModMixinInfo... infos) {
        Collections.addAll(INSTANCE.mixinInfos, infos);
    }

    public static Optional<ModMixinInfo> getByMixinClass(String mixinClassName) {
        return INSTANCE.mixinInfos.stream()
                .filter(info -> info.getMixinClasses().contains(mixinClassName))
                .findFirst();
    }

    public static Set<ModMixinInfo> getMixinInfos() {
        return INSTANCE.mixinInfos;
    }
}
