package dev.aika.smsn.fabric.mixin.ipn;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = org.anti_ad.mc.ipnext.IPNInfoManager$doCheckVersion$$inlined$timer$default$1.class, remap = false)
public abstract class IPNInfoManager$doCheckVersion$$inlined$timer$default$1Mixin {
    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    public void dontRun(CallbackInfo ci) {
        if (!SMSN.CONFIG.ipnUpdateCheckAndUserTracking()) ci.cancel();
    }
}
