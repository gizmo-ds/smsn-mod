package dev.aika.smsn.forge.mixin;

import dev.aika.smsn.utils.MapUtils;

import java.util.Map;

public class MixinInfo {
    public static Map<String, String> getMixinClassNames() {
        return MapUtils.createMap(
                "dev.aika.smsn.forge.mixin.aetherteam.UserData$ServerMixin", "nitrogen",
                "dev.aika.smsn.forge.mixin.alex.CitadelConstantsMixin", "citadel",
                "dev.aika.smsn.forge.mixin.alex.CitadelWebHelperMixin", "citadel",
                "dev.aika.smsn.forge.mixin.bagus_lib.TierHelperMixin", "bagus_lib",
                "dev.aika.smsn.forge.mixin.blueprint.RewardHandlerMixin", "blueprint",
                "dev.aika.smsn.forge.mixin.botania.ContributorListMixin", "botania",
                "dev.aika.smsn.forge.mixin.enigmatic_legacy.DevotedBelieversHandlerMixin", "enigmaticlegacy",
                "dev.aika.smsn.forge.mixin.enigmatic_legacy.EnigmaticUpdateHandlerMixin", "enigmaticlegacy",
                "dev.aika.smsn.forge.mixin.immersive_engineering.ImmersiveEngineeringMixin", "immersiveengineering",
                "dev.aika.smsn.forge.mixin.ipn.IPNInfoManagerMixin", "inventoryprofilesnext",
                "dev.aika.smsn.forge.mixin.obscure.ObscuriaCollectionMixin", "obscure_api",
                "dev.aika.smsn.forge.mixin.obscure.ObscuriaCollectionMixin$Mod", "obscure_api",
                "dev.aika.smsn.forge.mixin.projecte.ThreadCheckUUIDMixin", "projecte",
                "dev.aika.smsn.forge.mixin.quark.ThreadContributorListLoaderMixin", "quark",
                "dev.aika.smsn.forge.mixin.quark.QButtonMixin", "quark",
                "dev.aika.smsn.forge.mixin.supplementaries.CreditsMixin", "supplementaries",
                "dev.aika.smsn.forge.mixin.titanium.RewardMixin", "titanium",
                "dev.aika.smsn.forge.mixin.xaero.minimap.InternetMixin", "xaerominimap",
                "dev.aika.smsn.forge.mixin.xaero.minimap.PatreonMixin", "xaerominimap",
                "dev.aika.smsn.forge.mixin.xaero.world_map.InternetMixin", "xaeroworldmap",
                "dev.aika.smsn.forge.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap"
        );
    }
}
