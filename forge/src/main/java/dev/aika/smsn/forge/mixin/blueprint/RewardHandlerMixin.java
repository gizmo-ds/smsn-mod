package dev.aika.smsn.forge.mixin.blueprint;

import com.teamabnormals.blueprint.client.RewardHandler;
import com.teamabnormals.blueprint.core.BlueprintConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(value = RewardHandler.class, remap = false)
public abstract class RewardHandlerMixin {
    @Inject(method = "clientSetup", at = @At("HEAD"), cancellable = true)
    private static void onClientSetup(FMLClientSetupEvent event, CallbackInfo ci) {
        // Respect user settings
        if (!BlueprintConfig.CLIENT.slabfishSettings.enabled.get()) ci.cancel();
    }
}
