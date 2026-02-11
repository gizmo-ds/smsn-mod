package dev.aika.smsn.fabric.mixin.xxrexraptorxx.additionalstructures;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xxrexraptorxx.additionalstructures.Events;

@Mixin(value = Events.class, remap = false)
public abstract class EventsMixin {
    @Inject(method = "lambda$register$8", at = @At("HEAD"), cancellable = true)
    private static void register(CallbackInfo ci) {
        if (!SMSN.CONFIG.isStopModRepostsMessages()) ci.cancel();
    }
}
