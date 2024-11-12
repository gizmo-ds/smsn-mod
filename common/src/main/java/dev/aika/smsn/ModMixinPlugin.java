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
        switch (targetClassName) {
            case "com.github.alexthe666.citadel.CitadelConstants", "com.github.alexthe666.citadel.web.WebHelper":
                return SMSNPlatform.isModLoaded("citadel");
            case "vazkii.quark.base.handler.ContributorRewardHandler$ThreadContributorListLoader":
                return SMSNPlatform.isModLoaded("quark");
            case "xaero.common.misc.Internet", "xaero.common.patreon.Patreon":
                return SMSNPlatform.isModLoaded("xaerominimap");
            case "xaero.map.misc.Internet", "xaero.map.patreon.Patreon":
                return SMSNPlatform.isModLoaded("xaeroworldmap");
            default:
                return true;
        }
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
