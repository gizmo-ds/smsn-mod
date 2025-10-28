package dev.aika.smsn.fabric.mixin.immersiveportals;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import qouteall.imm_ptl.core.compat.IPModInfoChecking;

@Mixin(value = IPModInfoChecking.class, remap = false)
public abstract class IPModInfoCheckingMixin {
    @Inject(method = "fetchImmPtlInfoFromInternet", at = @At("HEAD"), cancellable = true)
    private static void fetchImmPtlInfoFromInternet(CallbackInfoReturnable<IPModInfoChecking.ImmPtlInfo> cir) {
        if (!SMSN.CONFIG.isImmersiveportalsModInfoChecking()) cir.setReturnValue(null);
    }
}
