package dev.aika.smsn.forge.mixin.ad_astra;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = earth.terrarium.adastra.common.utils.radio.StationLoader.class, remap = false)
public abstract class StationLoaderMixin {
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void init(CallbackInfo ci) {
        if (!SMSN.CONFIG.isAdAstraStation()) ci.cancel();
    }
}
