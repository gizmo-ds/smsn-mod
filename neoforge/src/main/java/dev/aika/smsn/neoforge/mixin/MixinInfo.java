package dev.aika.smsn.neoforge.mixin;

import dev.aika.smsn.utils.MapUtils;

import java.util.Map;

public class MixinInfo {
    @SuppressWarnings("SpellCheckingInspection")
    public static Map<String, String> getMixinClassNames() {
        return MapUtils.createMap(
                "dev.aika.smsn.neoforge.mixin.actuallyadditions.ThreadSpecialFetcherMixin", "actuallyadditions",
                "dev.aika.smsn.neoforge.mixin.aetherteam.UserData$ServerMixin", "nitrogen",
                "dev.aika.smsn.neoforge.mixin.bagus_lib.TierHelperMixin", "bagus_lib",
                "dev.aika.smsn.neoforge.mixin.blueprint.RewardHandlerMixin", "blueprint",
                "dev.aika.smsn.neoforge.mixin.exposure.GildedMixin", "exposure",
                "dev.aika.smsn.neoforge.mixin.exposure.PatreonMixin", "exposure",
                "dev.aika.smsn.neoforge.mixin.imb11.mru.APIMixin", "mru",
                "dev.aika.smsn.neoforge.mixin.immersive_engineering.ImmersiveEngineeringMixin", "immersiveengineering",
                "dev.aika.smsn.neoforge.mixin.immersivecaves.DiscordInviteOnJoinProcedureMixin", "immersivecaves",
                "dev.aika.smsn.neoforge.mixin.ipn.IPNInfoManagerMixin", "inventoryprofilesnext",
                "dev.aika.smsn.neoforge.mixin.iris.UpdateCheckerMixin", "iris",
                "dev.aika.smsn.neoforge.mixin.petrolpark.BadgeHandlerMixin", "petrolpark",
                "dev.aika.smsn.neoforge.mixin.placebo.TrailsManagerMixin", "placebo",
                "dev.aika.smsn.neoforge.mixin.placebo.WingsManagerMixin", "placebo",
                "dev.aika.smsn.neoforge.mixin.ribbits.SupportersJSONMixin", "ribbits",
                "dev.aika.smsn.neoforge.mixin.supplementaries.CreditsMixin", "supplementaries",
                "dev.aika.smsn.neoforge.mixin.titanium.RewardMixin", "titanium",
                "dev.aika.smsn.neoforge.mixin.xaero.minimap.InternetMixin", "xaerominimap",
                "dev.aika.smsn.neoforge.mixin.xaero.minimap.PatreonMixin", "xaerominimap",
                "dev.aika.smsn.neoforge.mixin.xaero.world_map.InternetMixin", "xaeroworldmap",
                "dev.aika.smsn.neoforge.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap"
        );
    }
}
