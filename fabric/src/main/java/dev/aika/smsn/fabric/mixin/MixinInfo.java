package dev.aika.smsn.fabric.mixin;

import java.util.HashMap;
import java.util.Map;

public class MixinInfo {
    @SuppressWarnings("SpellCheckingInspection")
    public static Map<String, String> getMixinClassNames() {
        return new HashMap<>() {{
            put("dev.aika.smsn.fabric.mixin.supplementaries.CreditsMixin", "supplementaries");

            put("dev.aika.smsn.fabric.mixin.xaero.minimap.InternetMixin", "xaerominimap");
            put("dev.aika.smsn.fabric.mixin.xaero.minimap.PatreonMixin", "xaerominimap");
            put("dev.aika.smsn.fabric.mixin.xaero.world_map.InternetMixin", "xaeroworldmap");
            put("dev.aika.smsn.fabric.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap");

            put("dev.aika.smsn.fabric.mixin.botania.ContributorListMixin", "botania");

            put("dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doCheckVersion$$inlined$timer$default$1Mixin", "inventoryprofilesnext");
            put("dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doSessionKeepAlive$$inlined$timer$default$1Mixin", "inventoryprofilesnext");

            put("dev.aika.smsn.fabric.mixin.iris.UpdateCheckerMixin", "iris");

            put("dev.aika.smsn.fabric.mixin.ad_astra.StationLoaderMixin", "ad_astra");

            put("dev.aika.smsn.fabric.mixin.exposure.GildedMixin", "exposure");
            put("dev.aika.smsn.fabric.mixin.exposure.PatreonMixin", "exposure");
        }};
    }
}
