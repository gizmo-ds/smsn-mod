package dev.aika.smsn.fabric;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.SMSNPlatform;
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
    private static final Marker MARKER = MarkerFactory.getMarker("fabric.ModMixinPlugin");

    @Override
    public void onLoad(String s) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return switch (targetClassName) {
            case "org.anti_ad.mc.ipnext.IPNInfoManager$doCheckVersion$$inlined$timer$default$1",
                 "org.anti_ad.mc.ipnext.IPNInfoManager$doSessionKeepAlive$$inlined$timer$default$1" ->
                    SMSNPlatform.isModLoaded("inventoryprofilesnext");
            default -> {
                log.warn(MARKER, "Unknown mixin target class: {}", targetClassName);
                yield false;
            }
        };
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {
    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {
    }
}
