package dev.aika.smsn.mixin.fabric;

import dev.aika.smsn.mixin.ModMixinInfo;
import dev.aika.smsn.mixin.ModMixinManager;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class MixinPlatformImpl {
    public static void register() {
        ModMixinManager.addInfo(
                ModMixinInfo.create("ad_astra",
                        "dev.aika.smsn.fabric.mixin.ad_astra.StationLoaderMixin"
                ),
                ModMixinInfo.create("nitrogen",
                        "dev.aika.smsn.fabric.mixin.aetherteam.UserData$ServerMixin"
                ),
                ModMixinInfo.create("botania",
                        "dev.aika.smsn.fabric.mixin.botania.ContributorListMixin"
                ),
                ModMixinInfo.create("exposure",
                        "dev.aika.smsn.fabric.mixin.exposure.GildedMixin",
                        "dev.aika.smsn.fabric.mixin.exposure.PatreonMixin"
                ),
                ModMixinInfo.create("mru",
                        "dev.aika.smsn.fabric.mixin.imb11.mru.APIMixin"
                ),
                ModMixinInfo.create("inventoryprofilesnext",
                        "dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doCheckVersion$$inlined$timer$default$1Mixin",
                        "dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doSessionKeepAlive$$inlined$timer$default$1Mixin"
                ),
                ModMixinInfo.create("iris",
                        "dev.aika.smsn.fabric.mixin.iris.UpdateCheckerMixin"
                ),
                ModMixinInfo.create("ribbits",
                        "dev.aika.smsn.fabric.mixin.ribbits.SupportersJSONMixin"
                ),
                ModMixinInfo.create("supplementaries",
                        "dev.aika.smsn.fabric.mixin.supplementaries.CreditsMixin"
                ),
                ModMixinInfo.create("tenshilib",
                        "dev.aika.smsn.fabric.mixin.tenshilib.PatreonDataManagerMixin"
                ),
                ModMixinInfo.create("xaerominimap",
                        "dev.aika.smsn.fabric.mixin.xaero.minimap.InternetMixin",
                        "dev.aika.smsn.fabric.mixin.xaero.minimap.PatreonMixin"
                ),
                ModMixinInfo.create("xaeroworldmap",
                        "dev.aika.smsn.fabric.mixin.xaero.world_map.InternetMixin",
                        "dev.aika.smsn.fabric.mixin.xaero.world_map.PatreonMixin"
                )
        );
    }
}
