package dev.aika.smsn.mixin.xaero.minimap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.IXaeroMinimap;

@Mixin(value = xaero.common.misc.Internet.class, remap = false)
public class InternetMixin {
    @Inject(method = "checkModVersion", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onCheckModVersion(IXaeroMinimap modMain, CallbackInfo ci) {
        ci.cancel();
    }
}
