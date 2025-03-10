package dev.aika.smsn;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

import static dev.aika.smsn.SMSNPlatform.isModLoaded;

public class ModMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @SuppressWarnings({"CommentedOutCode", "EnhancedSwitchMigration"})
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        switch (targetClassName) {
            // Common
//            case "vazkii.botania.common.handler.ContributorList":
//                return isModLoaded("botania");
            case "net.mehvahdjukaar.supplementaries.common.utils.Credits",
                 "net.mehvahdjukaar.supplementaries.Supplementaries":
                return isModLoaded("supplementaries");
            case "xaero.common.misc.Internet", "xaero.common.patreon.Patreon":
                return isModLoaded("xaerominimap");
            case "xaero.map.misc.Internet", "xaero.map.patreon.Patreon":
                return isModLoaded("xaeroworldmap");
            // Fabric
            case "org.anti_ad.mc.ipnext.IPNInfoManager$doCheckVersion$$inlined$timer$default$1",
                 "org.anti_ad.mc.ipnext.IPNInfoManager$doSessionKeepAlive$$inlined$timer$default$1":
                return isModLoaded("inventoryprofilesnext");
            // NeoForge
            case "com.aetherteam.nitrogen.api.users.UserData$Server":
                return isModLoaded("nitrogen");
//            case "com.github.alexthe666.citadel.CitadelConstants", "com.github.alexthe666.citadel.web.WebHelper":
//                return SMSNPlatform.isModLoaded("citadel");
//            case "com.github.alexmodguy.alexscaves.server.misc.WebHelper":
//                return SMSNPlatform.isModLoaded("alexscaves");
            case "bagu_chan.bagus_lib.util.reward.TierHelper":
                return isModLoaded("bagus_lib");
//            case "com.teamabnormals.blueprint.client.RewardHandler":
//                return SMSNPlatform.isModLoaded("blueprint");
            case "org.anti_ad.mc.ipnext.IPNInfoManager":
                return isModLoaded("inventoryprofilesnext");
            case "dev.latvian.mods.kubejs.KubeJSModEventHandler":
                return isModLoaded("kubejs");
//            case "com.petrolpark.badge.BadgeHandler":
//                return SMSNPlatform.isModLoaded("petrolpark");
//            case "org.violetmoon.quark.base.handler.ContributorRewardHandler$ThreadContributorListLoader":
//                return SMSNPlatform.isModLoaded("quark");
            default:
                SMSN.LOGGER.warn("Unknown mixin target class: {}", targetClassName);
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
