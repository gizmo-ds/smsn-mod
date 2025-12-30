package dev.aika.smsn.mixin.forge;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.mixin.ModMixinInfo;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class MixinPlatformImpl {
    public static void register() {
        SMSN.MixinManager.addInfo(
                new ModMixinInfo("ad_astra",
                        "dev.aika.smsn.forge.mixin.ad_astra.StationLoaderMixin"
                ),
                new ModMixinInfo("additionalstructures",
                        "dev.aika.smsn.forge.mixin.additionalstructures.EventsMixin"
                ),
                new ModMixinInfo("nitrogen",
                        "dev.aika.smsn.forge.mixin.aetherteam.UserData$ServerMixin"
                ),
                new ModMixinInfo("alexscaves",
                        "dev.aika.smsn.forge.mixin.alex.AlexsCavesWebHelperMixin"
                ),
                new ModMixinInfo("citadel",
                        "dev.aika.smsn.forge.mixin.alex.CitadelConstantsMixin",
                        "dev.aika.smsn.forge.mixin.alex.CitadelWebHelperMixin"
                ),
                new ModMixinInfo("ars_nouveau",
                        "dev.aika.smsn.forge.mixin.arsnouveau.RewardsMixin"
                ),
                new ModMixinInfo("bagus_lib",
                        "dev.aika.smsn.forge.mixin.bagus_lib.TierHelperMixin"
                ),
                new ModMixinInfo("blueprint",
                        "dev.aika.smsn.forge.mixin.blueprint.RewardHandlerMixin"
                ),
                new ModMixinInfo("railways",
                        "dev.aika.smsn.forge.mixin.create_snr.DevCapeUtilsMixin"
                ),
                new ModMixinInfo("tram_additions",
                        "dev.aika.smsn.forge.mixin.create_tram_additions.WorldTickMixin",
                        "dev.aika.smsn.forge.mixin.create_tram_additions.TramAdditionsMixin"
                ),
                new ModMixinInfo("enigmaticlegacy",
                        "dev.aika.smsn.forge.mixin.enigmatic_legacy.DevotedBelieversHandlerMixin",
                        "dev.aika.smsn.forge.mixin.enigmatic_legacy.EnigmaticUpdateHandlerMixin"
                ),
                new ModMixinInfo("exposure",
                        "dev.aika.smsn.forge.mixin.exposure.GildedMixin",
                        "dev.aika.smsn.forge.mixin.exposure.PatreonMixin"
                ),
                new ModMixinInfo("hexerei",
                        "dev.aika.smsn.forge.mixin.hexerei.HexereiSupporterBenefitsMixin"
                ),
                new ModMixinInfo("mru",
                        "dev.aika.smsn.forge.mixin.imb11.mru.APIMixin"
                ),
                new ModMixinInfo("immersiveengineering",
                        "dev.aika.smsn.forge.mixin.immersive_engineering.ImmersiveEngineeringMixin"
                ),
                new ModMixinInfo("immersivecaves",
                        "dev.aika.smsn.forge.mixin.immersivecaves.DiscordInviteOnJoinProcedureMixin"
                ),
                new ModMixinInfo("imm_ptl_core",
                        "dev.aika.smsn.forge.mixin.immersiveportals.IPModInfoCheckingMixin"
                ),
                new ModMixinInfo("inventoryprofilesnext",
                        "dev.aika.smsn.forge.mixin.ipn.IPNInfoManagerMixin"
                ),
                new ModMixinInfo("kiwi",
                        "dev.aika.smsn.forge.mixin.kiwi.KiwiTierProviderMixin",
                        "dev.aika.smsn.forge.mixin.kiwi.JsonTierProviderMixin"
                ),
                new ModMixinInfo("majruszlibrary",
                        "dev.aika.smsn.forge.mixin.majrusz.VersionCheckerMixin"
                ),
                new ModMixinInfo("obscure_api",
                        "dev.aika.smsn.forge.mixin.obscure.ObscuriaCollectionMixin",
                        "dev.aika.smsn.forge.mixin.obscure.ObscuriaCollectionMixin$Mod"
                ),
                new ModMixinInfo("petrolpark",
                        "dev.aika.smsn.forge.mixin.petrolpark.BadgeHandlerMixin"
                ),
                new ModMixinInfo("placebo",
                        "dev.aika.smsn.forge.mixin.placebo.TrailsManagerMixin",
                        "dev.aika.smsn.forge.mixin.placebo.WingsManagerMixin"
                ),
                new ModMixinInfo("projecte",
                        "dev.aika.smsn.forge.mixin.projecte.ThreadCheckUUIDMixin"
                ),
                new ModMixinInfo("quark",
                        "dev.aika.smsn.forge.mixin.vazkii.quark.ThreadContributorListLoaderMixin",
                        "dev.aika.smsn.forge.mixin.vazkii.quark.QButtonMixin"
                ),
                new ModMixinInfo("zeta",
                        "dev.aika.smsn.forge.mixin.vazkii.zeta.ZetaModuleManagerMixin",
                        "import dev.aika.smsn.forge.mixin.vazkii.quark.ModelHandlerMixin;ModelHandlerMixin"
                ),
                new ModMixinInfo("botania",
                        "dev.aika.smsn.forge.mixin.vazkii.botania.ContributorListMixin"
                ),
                new ModMixinInfo("ribbits",
                        "dev.aika.smsn.forge.mixin.ribbits.RibbitEntityMixin",
                        "dev.aika.smsn.forge.mixin.ribbits.SupportersJSONMixin"
                ),
                new ModMixinInfo("supplementaries",
                        "dev.aika.smsn.forge.mixin.supplementaries.CreditsMixin"
                ),
                new ModMixinInfo("tenshilib",
                        "dev.aika.smsn.forge.mixin.tenshilib.PatreonDataManagerMixin"
                ),
                new ModMixinInfo("titanium",
                        "dev.aika.smsn.forge.mixin.titanium.RewardMixin"
                ),
                new ModMixinInfo("voidscape",
                        "dev.aika.smsn.forge.mixin.voidscape.DonatorHandlerMixin"
                ),
                new ModMixinInfo("xaerominimap",
                        "dev.aika.smsn.forge.mixin.xaero.minimap.InternetMixin",
                        "dev.aika.smsn.forge.mixin.xaero.minimap.PatreonMixin"
                ),
                new ModMixinInfo("xaeroworldmap",
                        "dev.aika.smsn.forge.mixin.xaero.world_map.InternetMixin",
                        "dev.aika.smsn.forge.mixin.xaero.world_map.PatreonMixin"
                )
        );
    }
}
