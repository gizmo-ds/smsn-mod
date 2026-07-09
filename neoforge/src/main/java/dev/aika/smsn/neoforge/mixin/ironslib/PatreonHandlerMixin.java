package dev.aika.smsn.neoforge.mixin.ironslib;

import dev.aika.smsn.SMSN;
import io.redspace.ironslib.patreon.PatreonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PatreonHandler.class, remap = false)
public abstract class PatreonHandlerMixin {
    @Inject(method = "doDataFetch", at = @At("HEAD"), cancellable = true)
    private void doDataFetch(CallbackInfo ci) {
        if (!SMSN.CONFIG.isIronsLibPatreon()) ci.cancel();
    }
}