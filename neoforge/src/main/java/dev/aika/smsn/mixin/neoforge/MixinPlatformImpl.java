package dev.aika.smsn.mixin.neoforge;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.mixin.ModMixinInfo;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class MixinPlatformImpl {
    public static void register() {
        SMSN.MixinManager.addInfo(
                new ModMixinInfo("actuallyadditions",
                        "dev.aika.smsn.neoforge.mixin.actuallyadditions.ThreadSpecialFetcherMixin"
                ),
                new ModMixinInfo("additionalstructures",
                        "dev.aika.smsn.neoforge.mixin.additionalstructures.EventsMixin"
                ),
                new ModMixinInfo("nitrogen",
                        "dev.aika.smsn.neoforge.mixin.aetherteam.UserData$ServerMixin"
                ),
                new ModMixinInfo("ars_nouveau",
                        "dev.aika.smsn.neoforge.mixin.arsnouveau.RewardsMixin"
                ),
                new ModMixinInfo("bagus_lib",
                        "dev.aika.smsn.neoforge.mixin.bagus_lib.TierHelperMixin"
                ),
                new ModMixinInfo("blueprint",
                        "dev.aika.smsn.neoforge.mixin.blueprint.RewardHandlerMixin"
                ),
                new ModMixinInfo("exposure",
                        "dev.aika.smsn.neoforge.mixin.exposure.GildedMixin",
                        "dev.aika.smsn.neoforge.mixin.exposure.PatreonMixin"
                ),
                new ModMixinInfo("hexerei",
                        "dev.aika.smsn.neoforge.mixin.hexerei.HexereiSupporterBenefitsMixin"
                ),
                new ModMixinInfo("mru",
                        "dev.aika.smsn.neoforge.mixin.imb11.mru.APIMixin"
                ),
                new ModMixinInfo("immersiveengineering",
                        "dev.aika.smsn.neoforge.mixin.immersive_engineering.ImmersiveEngineeringMixin"
                ),
                new ModMixinInfo("immersivecaves",
                        "dev.aika.smsn.neoforge.mixin.immersivecaves.DiscordInviteOnJoinProcedureMixin"
                ),
                new ModMixinInfo("immersive_portals_core",
                        "dev.aika.smsn.neoforge.mixin.immersiveportals.IPModInfoCheckingMixin"
                ),
                new ModMixinInfo("inventoryprofilesnext",
                        "dev.aika.smsn.neoforge.mixin.ipn.IPNInfoManagerMixin"
                ),
                new ModMixinInfo("iris",
                        "dev.aika.smsn.neoforge.mixin.iris.UpdateCheckerMixin"
                ),
                new ModMixinInfo("petrolpark",
                        "dev.aika.smsn.neoforge.mixin.petrolpark.BadgeHandlerMixin"
                ),
                new ModMixinInfo("placebo",
                        "dev.aika.smsn.neoforge.mixin.placebo.TrailsManagerMixin",
                        "dev.aika.smsn.neoforge.mixin.placebo.WingsManagerMixin"
                ),
                new ModMixinInfo("projecte",
                        "dev.aika.smsn.neoforge.mixin.projecte.ThreadCheckUUIDMixin"
                ),
                new ModMixinInfo("quark",
                        "dev.aika.smsn.neoforge.mixin.quark.QButtonMixin",
                        "dev.aika.smsn.neoforge.mixin.quark.ThreadContributorListLoaderMixin"
                ),
                new ModMixinInfo("ribbits",
                        "dev.aika.smsn.neoforge.mixin.ribbits.SupportersJSONMixin"
                ),
                new ModMixinInfo("supplementaries",
                        "dev.aika.smsn.neoforge.mixin.supplementaries.CreditsMixin"
                ),
                new ModMixinInfo("tenshilib",
                        "dev.aika.smsn.neoforge.mixin.tenshilib.PatreonDataManagerMixin"
                ),
                new ModMixinInfo("titanium",
                        "dev.aika.smsn.neoforge.mixin.titanium.RewardMixin"
                ),
                new ModMixinInfo("xaerominimap",
                        "dev.aika.smsn.neoforge.mixin.xaero.minimap.InternetMixin",
                        "dev.aika.smsn.neoforge.mixin.xaero.minimap.PatreonMixin"
                ),
                new ModMixinInfo("xaeroworldmap",
                        "dev.aika.smsn.neoforge.mixin.xaero.world_map.InternetMixin",
                        "dev.aika.smsn.neoforge.mixin.xaero.world_map.PatreonMixin"
                )
        );
    }
}
