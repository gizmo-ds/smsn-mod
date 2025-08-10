package dev.aika.smsn.neoforge.mixin;

import dev.aika.smsn.utils.MapUtils;

import java.util.Map;

public class MixinInfo {
    public static Map<String, String> getMixinClassNames() {
        return MapUtils.createMap(
                "dev.aika.smsn.neoforge.mixin.imb11.mru.APIMixin", "mru",
                "dev.aika.smsn.neoforge.mixin.ipn.IPNInfoManagerMixin", "inventoryprofilesnext",
                "dev.aika.smsn.neoforge.mixin.iris.UpdateCheckerMixin", "iris",
                "dev.aika.smsn.neoforge.mixin.xaero.minimap.InternetMixin", "xaerominimap",
                "dev.aika.smsn.neoforge.mixin.xaero.minimap.PatreonMixin", "xaerominimap",
                "dev.aika.smsn.neoforge.mixin.xaero.world_map.InternetMixin", "xaeroworldmap",
                "dev.aika.smsn.neoforge.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap"
        );
    }
}
