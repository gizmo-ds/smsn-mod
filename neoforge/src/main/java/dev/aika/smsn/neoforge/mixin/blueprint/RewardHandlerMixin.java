package dev.aika.smsn.neoforge.mixin.blueprint;

import com.teamabnormals.blueprint.core.BlueprintConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(value = com.teamabnormals.blueprint.client.RewardHandler.class, remap = false)
public abstract class RewardHandlerMixin {
    @Inject(method = "clientSetup", at = @At("HEAD"), cancellable = true)
    private static void onClientSetup(FMLClientSetupEvent event, CallbackInfo ci) {
        // Respect user settings
        if (!BlueprintConfig.CLIENT.slabfishSettings.enabled.get()) ci.cancel();
    }
}
