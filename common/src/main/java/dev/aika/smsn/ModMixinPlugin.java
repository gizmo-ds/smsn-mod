package dev.aika.smsn;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class ModMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return switch (targetClassName) {
            case "xaero.common.misc.Internet", "xaero.common.patreon.Patreon" ->
                    SMSNPlatform.isModLoaded("xaerominimap");
            case "xaero.map.misc.Internet", "xaero.map.patreon.Patreon" -> SMSNPlatform.isModLoaded("xaeroworldmap");
            case "net.mehvahdjukaar.supplementaries.common.utils.Credits" ->
                    SMSNPlatform.isModLoaded("supplementaries");
            case "vazkii.botania.common.handler.ContributorList" -> SMSNPlatform.isModLoaded("botania");
            default -> {
                SMSN.LOGGER.warn("Unknown mixin target class: {}", targetClassName);
                yield false;
            }
        };
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
