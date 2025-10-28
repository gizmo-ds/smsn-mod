package dev.aika.smsn.mixin.fabric;

import dev.aika.smsn.SMSN;
import dev.aika.smsn.mixin.ModMixinInfo;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class MixinPlatformImpl {
    public static void register() {
        SMSN.MixinManager.addInfo(
                new ModMixinInfo("ad_astra",
                        "dev.aika.smsn.fabric.mixin.ad_astra.StationLoaderMixin"
                ),
                new ModMixinInfo("nitrogen",
                        "dev.aika.smsn.fabric.mixin.aetherteam.UserData$ServerMixin"
                ),
                new ModMixinInfo("botania",
                        "dev.aika.smsn.fabric.mixin.botania.ContributorListMixin"
                ),
                new ModMixinInfo("tram_additions",
                        "dev.aika.smsn.fabric.mixin.create_tram_additions.WorldTickMixin",
                        "dev.aika.smsn.fabric.mixin.create_tram_additions.TramAdditionsMixin"
                ),
                new ModMixinInfo("exposure",
                        "dev.aika.smsn.fabric.mixin.exposure.GildedMixin",
                        "dev.aika.smsn.fabric.mixin.exposure.PatreonMixin"
                ),
                new ModMixinInfo("mru",
                        "dev.aika.smsn.fabric.mixin.imb11.mru.APIMixin"
                ),
                new ModMixinInfo("immersive_portals_core",
                        "dev.aika.smsn.fabric.mixin.immersiveportals.IPModInfoCheckingMixin"
                ),
                new ModMixinInfo("inventoryprofilesnext",
                        "dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doCheckVersion$$inlined$timer$default$1Mixin",
                        "dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doSessionKeepAlive$$inlined$timer$default$1Mixin"
                ),
                new ModMixinInfo("iris",
                        "dev.aika.smsn.fabric.mixin.iris.UpdateCheckerMixin"
                ),
                new ModMixinInfo("ribbits",
                        "dev.aika.smsn.fabric.mixin.ribbits.SupportersJSONMixin"
                ),
                new ModMixinInfo("supplementaries",
                        "dev.aika.smsn.fabric.mixin.supplementaries.CreditsMixin"
                ),
                new ModMixinInfo("tenshilib",
                        "dev.aika.smsn.fabric.mixin.tenshilib.PatreonDataManagerMixin"
                ),
                new ModMixinInfo("xaerominimap",
                        "dev.aika.smsn.fabric.mixin.xaero.minimap.InternetMixin",
                        "dev.aika.smsn.fabric.mixin.xaero.minimap.PatreonMixin"
                ),
                new ModMixinInfo("xaeroworldmap",
                        "dev.aika.smsn.fabric.mixin.xaero.world_map.InternetMixin",
                        "dev.aika.smsn.fabric.mixin.xaero.world_map.PatreonMixin"
                )
        );
    }
}
