package dev.aika.smsn.mixin;

import lombok.Getter;

import java.util.*;

public class ModMixinManager {
    @Getter
    private final Set<ModMixinInfo> mixinInfos = new LinkedHashSet<>();

    public void addInfo(ModMixinInfo... infos) {
        Collections.addAll(mixinInfos, infos);
    }

    public List<ModMixinInfo> getByMixinClass(String mixinClassName) {
        return mixinInfos.stream()
                .filter(info -> info.mixinClasses().contains(mixinClassName))
                .toList();
    }
}
