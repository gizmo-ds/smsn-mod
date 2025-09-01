package dev.aika.smsn.forge.mixin;

import dev.aika.smsn.utils.MapUtils;

import java.util.Map;

public class MixinInfo {
    public static Map<String, String> getMixinClassNames() {
        //noinspection SpellCheckingInspection
        return MapUtils.createMap(
                "dev.aika.smsn.forge.mixin.ad_astra.StationLoaderMixin", "ad_astra",
                "dev.aika.smsn.forge.mixin.additionalstructures.EventsMixin", "additionalstructures",
                "dev.aika.smsn.forge.mixin.aetherteam.UserData$ServerMixin", "nitrogen",
                "dev.aika.smsn.forge.mixin.alex.AlexsCavesWebHelperMixin", "alexscaves",
                "dev.aika.smsn.forge.mixin.alex.CitadelConstantsMixin", "citadel",
                "dev.aika.smsn.forge.mixin.alex.CitadelWebHelperMixin", "citadel",
                "dev.aika.smsn.forge.mixin.ba.BlueArchivescraftModMixin", "blue_archivescraft",
                "dev.aika.smsn.forge.mixin.bagus_lib.TierHelperMixin", "bagus_lib",
                "dev.aika.smsn.forge.mixin.blueprint.RewardHandlerMixin", "blueprint",
                "dev.aika.smsn.forge.mixin.botania.ContributorListMixin", "botania",
                "dev.aika.smsn.forge.mixin.enigmatic_legacy.DevotedBelieversHandlerMixin", "enigmaticlegacy",
                "dev.aika.smsn.forge.mixin.enigmatic_legacy.EnigmaticUpdateHandlerMixin", "enigmaticlegacy",
                "dev.aika.smsn.forge.mixin.exposure.GildedMixin", "exposure",
                "dev.aika.smsn.forge.mixin.exposure.PatreonMixin", "exposure",
                "dev.aika.smsn.forge.mixin.hexerei.HexereiSupporterBenefitsMixin", "hexerei",
                "dev.aika.smsn.forge.mixin.imb11.mru.APIMixin", "mru",
                "dev.aika.smsn.forge.mixin.immersive_engineering.ImmersiveEngineeringMixin", "immersiveengineering",
                "dev.aika.smsn.forge.mixin.immersivecaves.DiscordInviteOnJoinProcedureMixin", "immersivecaves",
                "dev.aika.smsn.forge.mixin.ipn.IPNInfoManagerMixin", "inventoryprofilesnext",
                "dev.aika.smsn.forge.mixin.obscure.ObscuriaCollectionMixin", "obscure_api",
                "dev.aika.smsn.forge.mixin.obscure.ObscuriaCollectionMixin$Mod", "obscure_api",
                "dev.aika.smsn.forge.mixin.petrolpark.BadgeHandlerMixin", "petrolpark",
                "dev.aika.smsn.forge.mixin.placebo.TrailsManagerMixin", "placebo",
                "dev.aika.smsn.forge.mixin.placebo.WingsManagerMixin", "placebo",
                "dev.aika.smsn.forge.mixin.projecte.ThreadCheckUUIDMixin", "projecte",
                "dev.aika.smsn.forge.mixin.quark.ThreadContributorListLoaderMixin", "quark",
                "dev.aika.smsn.forge.mixin.quark.QButtonMixin", "quark",
                "dev.aika.smsn.forge.mixin.ribbits.SupportersJSONMixin", "ribbits",
                "dev.aika.smsn.forge.mixin.supplementaries.CreditsMixin", "supplementaries",
                "dev.aika.smsn.forge.mixin.tenshilib.PatreonDataManagerMixin", "tenshilib",
                "dev.aika.smsn.forge.mixin.titanium.RewardMixin", "titanium",
                "dev.aika.smsn.forge.mixin.voidscape.DonatorHandlerMixin", "voidscape",
                "dev.aika.smsn.forge.mixin.xaero.minimap.InternetMixin", "xaerominimap",
                "dev.aika.smsn.forge.mixin.xaero.minimap.PatreonMixin", "xaerominimap",
                "dev.aika.smsn.forge.mixin.xaero.world_map.InternetMixin", "xaeroworldmap",
                "dev.aika.smsn.forge.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap"
        );
    }
}
