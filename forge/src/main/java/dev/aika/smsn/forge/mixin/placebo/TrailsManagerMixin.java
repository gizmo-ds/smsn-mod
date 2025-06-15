package dev.aika.smsn.forge.mixin.placebo;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = shadows.placebo.patreon.TrailsManager.class, remap = false)
public abstract class TrailsManagerMixin {
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void init(CallbackInfo ci) {
        if (!SMSN.CONFIG.isPlaceboTrails()) ci.cancel();
    }
}
