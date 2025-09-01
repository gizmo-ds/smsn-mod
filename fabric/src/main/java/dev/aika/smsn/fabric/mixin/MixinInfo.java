package dev.aika.smsn.fabric.mixin;

import dev.aika.smsn.utils.MapUtils;

import java.util.Map;

public class MixinInfo {
    public static Map<String, String> getMixinClassNames() {
        return MapUtils.createMap(
                "dev.aika.smsn.fabric.mixin.aetherteam.UserData$ServerMixin", "nitrogen",
                "dev.aika.smsn.fabric.mixin.exposure.GildedMixin", "exposure",
                "dev.aika.smsn.fabric.mixin.exposure.PatreonMixin", "exposure",
                "dev.aika.smsn.fabric.mixin.imb11.mru.APIMixin", "mru",
                "dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doCheckVersion$$inlined$timer$default$1Mixin", "inventoryprofilesnext",
                "dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doSessionKeepAlive$$inlined$timer$default$1Mixin", "inventoryprofilesnext",
                "dev.aika.smsn.fabric.mixin.iris.UpdateCheckerMixin", "iris",
                "dev.aika.smsn.fabric.mixin.ribbits.SupportersJSONMixin", "ribbits",
                "dev.aika.smsn.fabric.mixin.supplementaries.CreditsMixin", "supplementaries",
                "dev.aika.smsn.fabric.mixin.tenshilib.PatreonDataManagerMixin", "tenshilib",
                "dev.aika.smsn.fabric.mixin.xaero.minimap.InternetMixin", "xaerominimap",
                "dev.aika.smsn.fabric.mixin.xaero.minimap.PatreonMixin", "xaerominimap",
                "dev.aika.smsn.fabric.mixin.xaero.world_map.InternetMixin", "xaeroworldmap",
                "dev.aika.smsn.fabric.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap"
        );
    }
}
