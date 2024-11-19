package dev.aika.smsn.forge.mixin.ipn;

import org.anti_ad.mc.ipnext.IPNInfoManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = IPNInfoManager.class, remap = false)
public abstract class IPNInfoManagerMixin {
    @Inject(method = "doCheckVersion", at = @At("HEAD"), cancellable = true)
    public void dontDoCheckVersion(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "doSessionKeepAlive", at = @At("HEAD"), cancellable = true)
    public void dontDoSessionKeepAlive(CallbackInfo ci) {
        ci.cancel();
    }
}
