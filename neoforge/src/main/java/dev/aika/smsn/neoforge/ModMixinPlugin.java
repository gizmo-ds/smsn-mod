package dev.aika.smsn.neoforge;

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
    private static final Marker MARKER = MarkerFactory.getMarker("forge.ModMixinPlugin");

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
            case "com.aetherteam.nitrogen.api.users.UserData$Server" -> SMSNPlatform.isModLoaded("nitrogen");
            case "com.github.alexthe666.citadel.CitadelConstants", "com.github.alexthe666.citadel.web.WebHelper" ->
                    SMSNPlatform.isModLoaded("citadel");
            case "com.github.alexmodguy.alexscaves.server.misc.WebHelper" -> SMSNPlatform.isModLoaded("alexscaves");
            case "bagu_chan.bagus_lib.util.TierHelper" -> SMSNPlatform.isModLoaded("bagus_lib");
            case "com.teamabnormals.blueprint.client.RewardHandler" -> SMSNPlatform.isModLoaded("blueprint");
            case "blusunrize.immersiveengineering.ImmersiveEngineering$ThreadContributorSpecialsDownloader" ->
                    SMSNPlatform.isModLoaded("immersiveengineering");
            case "org.anti_ad.mc.ipnext.IPNInfoManager" -> SMSNPlatform.isModLoaded("inventoryprofilesnext");
            case "com.obscuria.obscureapi.network.ObscuriaCollection",
                 "com.obscuria.obscureapi.network.ObscuriaCollection$Mod" -> SMSNPlatform.isModLoaded("obscure_api");
            case "com.petrolpark.badge.BadgeHandler" -> SMSNPlatform.isModLoaded("petrolpark");
            case "org.violetmoon.quark.base.handler.ContributorRewardHandler$ThreadContributorListLoader" ->
                    SMSNPlatform.isModLoaded("quark");
            case "com.aizistral.enigmaticlegacy.handlers.DevotedBelieversHandler",
                 "com.aizistral.enigmaticlegacy.handlers.EnigmaticUpdateHandler" ->
                    SMSNPlatform.isModLoaded("enigmaticlegacy");
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

