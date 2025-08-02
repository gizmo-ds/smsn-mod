package dev.aika.smsn.forge.mixin;

import java.util.HashMap;
import java.util.Map;

public class MixinInfo {
    @SuppressWarnings("SpellCheckingInspection")
    public static Map<String, String> getMixinClassNames() {
        return new HashMap<>() {{
            put("dev.aika.smsn.forge.mixin.quark.ThreadContributorListLoaderMixin", "quark");

            put("dev.aika.smsn.forge.mixin.blueprint.RewardHandlerMixin", "blueprint");

            put("dev.aika.smsn.forge.mixin.alex.CitadelWebHelperMixin", "citadel");

            put("dev.aika.smsn.forge.mixin.bagus_lib.TierHelperMixin", "bagus_lib");

            put("dev.aika.smsn.forge.mixin.enigmatic_legacy.DevotedBelieversHandlerMixin", "enigmaticlegacy");
            put("dev.aika.smsn.forge.mixin.enigmatic_legacy.EnigmaticUpdateHandlerMixin", "enigmaticlegacy");

            put("dev.aika.smsn.forge.mixin.immersive_engineering.ImmersiveEngineeringMixin", "immersiveengineering");

            put("dev.aika.smsn.forge.mixin.ipn.IPNInfoManagerMixin", "inventoryprofilesnext");

            put("dev.aika.smsn.forge.mixin.xaero.minimap.InternetMixin", "xaerominimap");
            put("dev.aika.smsn.forge.mixin.xaero.minimap.PatreonMixin", "xaerominimap");
            put("dev.aika.smsn.forge.mixin.xaero.world_map.InternetMixin", "xaeroworldmap");
            put("dev.aika.smsn.forge.mixin.xaero.world_map.PatreonMixin", "xaeroworldmap");

            put("dev.aika.smsn.forge.mixin.titanium.RewardMixin", "titanium");

            put("dev.aika.smsn.forge.mixin.botania.ContributorListMixin", "botania");
        }};
    }
}
