package dev.aika.smsn;

import dev.aika.smsn.mixin.MixinPlatform;
import dev.aika.smsn.mixin.ModMixinInfo;
import dev.aika.smsn.mixin.ModMixinManager;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class ModMixinPlugin implements IMixinConfigPlugin {
    private static final Logger log = SMSN.LOGGER;
    private static final Marker marker = MarkerFactory.getMarker("ModMixinPlugin");

    @Override
    public void onLoad(String mixinPackage) {
        MixinPlatform.register();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        final ModMixinInfo info = ModMixinManager.getByMixinClass(mixinClassName).orElse(null);
        if (info != null) return info.isModLoaded();

        log.warn(marker, "Unknown mixin class: {}", mixinClassName);
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
