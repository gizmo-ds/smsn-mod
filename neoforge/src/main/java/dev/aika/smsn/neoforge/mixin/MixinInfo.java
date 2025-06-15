package dev.aika.smsn.neoforge.mixin;

import java.util.HashMap;
import java.util.Map;

public class MixinInfo {
    @SuppressWarnings("SpellCheckingInspection")
    public static Map<String, String> getMixinClassNames() {
        return new HashMap<>() {{
            put("dev.aika.smsn.neoforge.mixin.blueprint.RewardHandlerMixin", "blueprint");

            put("dev.aika.smsn.neoforge.mixin.supplementaries.CreditsMixin", "supplementaries");

            put("dev.aika.smsn.neoforge.mixin.petrolpark.BadgeHandlerMixin", "petrolpark");

            put("dev.aika.smsn.neoforge.mixin.immersive_engineering.ImmersiveEngineeringMixin", "immersiveengineering");

            put("dev.aika.smsn.neoforge.mixin.ipn.IPNInfoManagerMixin", "inventoryprofilesnext");

            put("dev.aika.smsn.neoforge.mixin.bagus_lib.TierHelperMixin", "bagus_lib");

            put("dev.aika.smsn.neoforge.mixin.aetherteam.UserData$ServerMixin", "nitrogen");

            put("dev.aika.smsn.neoforge.mixin.placebo.TrailsManagerMixin", "placebo");
            put("dev.aika.smsn.neoforge.mixin.placebo.WingsManagerMixin", "placebo");

            put("dev.aika.smsn.neoforge.mixin.immersivecaves.DiscordInviteOnJoinProcedureMixin", "immersivecaves");

            put("dev.aika.smsn.neoforge.mixin.iris.UpdateCheckerMixin", "iris");

            put("dev.aika.smsn.neoforge.mixin.actuallyadditions.ThreadSpecialFetcherMixin", "actuallyadditions");
        }};
    }
}
