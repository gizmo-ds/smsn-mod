package dev.aika.smsn.fabric.mixin;

import java.util.HashMap;
import java.util.Map;

public class MixinInfo {
    @SuppressWarnings("SpellCheckingInspection")
    public static Map<String, String> getMixinClassNames() {
        return new HashMap<>() {{
            put("dev.aika.smsn.fabric.mixin.supplementaries.CreditsMixin", "supplementaries");

            put("dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doCheckVersion$$inlined$timer$default$1Mixin", "inventoryprofilesnext");
            put("dev.aika.smsn.fabric.mixin.ipn.IPNInfoManager$doSessionKeepAlive$$inlined$timer$default$1Mixin", "inventoryprofilesnext");

            put("dev.aika.smsn.fabric.mixin.iris.UpdateCheckerMixin", "iris");
        }};
    }
}
