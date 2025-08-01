package dev.aika.smsn.neoforge.mixin;

import java.util.HashMap;
import java.util.Map;

public class MixinInfo {
    @SuppressWarnings("SpellCheckingInspection")
    public static Map<String, String> getMixinClassNames() {
        return new HashMap<>() {{
            put("dev.aika.smsn.neoforge.mixin.ipn.IPNInfoManagerMixin", "inventoryprofilesnext");

            put("dev.aika.smsn.neoforge.mixin.iris.UpdateCheckerMixin", "iris");

            put("dev.aika.smsn.neoforge.mixin.xaero.minimap.InternetMixin", "xaerominimap");
            put("dev.aika.smsn.neoforge.mixin.xaero.minimap.PatreonMixin", "xaerominimap");
            put("dev.aika.smsn.neoforge.mixin.xaero.world_map.InternetMixin", "xaeroworldmap");
            put("dev.aika.smsn.neoforge.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap");

            put("dev.aika.smsn.neoforge.mixin.imb11.mru.APIMixin", "mru");
        }};
    }
}
