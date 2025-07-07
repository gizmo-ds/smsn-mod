package dev.aika.smsn.neoforge.mixin.xaero.world_map;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = xaero.map.misc.Internet.class, remap = false)
public abstract class InternetMixin {
    @Inject(method = "checkModVersion", at = @At("HEAD"), cancellable = true)
    private static void onCheckModVersion(CallbackInfo ci) {
        if (!SMSN.CONFIG.isXaeroMapVersionCheck()) ci.cancel();
    }
}
