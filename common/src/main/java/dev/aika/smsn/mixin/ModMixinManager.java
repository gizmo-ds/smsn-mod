package dev.aika.smsn.mixin;

import lombok.Getter;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class ModMixinManager {
    @Getter
    private final Set<ModMixinInfo> mixinInfos = new LinkedHashSet<>();

    public void addInfo(ModMixinInfo... infos) {
        Collections.addAll(mixinInfos, infos);
    }

    public Optional<ModMixinInfo> getByMixinClass(String mixinClassName) {
        return mixinInfos.stream()
                .filter(info -> info.getMixinClasses().contains(mixinClassName))
                .findFirst();
    }
}
