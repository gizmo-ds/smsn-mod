package dev.aika.smsn.mixin.xaero.world_map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = xaero.map.misc.Internet.class, remap = false)
public class InternetMixin {
    @Inject(method = "checkModVersion", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onCheckModVersion(CallbackInfo ci) {
        ci.cancel();
    }
}
