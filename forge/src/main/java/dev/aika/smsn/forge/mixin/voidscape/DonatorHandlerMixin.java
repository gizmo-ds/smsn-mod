package dev.aika.smsn.forge.mixin.voidscape;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tamaized.voidscape.network.DonatorHandler;

@Mixin(value = DonatorHandler.class, remap = false)
public abstract class DonatorHandlerMixin {
    @Inject(method = "start", at = @At("HEAD"), cancellable = true)
    private static void start(CallbackInfo ci) {
        if (!SMSN.CONFIG.isVoidscapeDonator()) ci.cancel();
    }
}
