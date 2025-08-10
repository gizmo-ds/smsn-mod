package dev.aika.smsn.forge.mixin.projecte;

import dev.aika.smsn.SMSN;
import moze_intel.projecte.network.ThreadCheckUUID;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ThreadCheckUUID.class, remap = false)
public abstract class ThreadCheckUUIDMixin {
    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    private void run(CallbackInfo ci) {
        if (!SMSN.CONFIG.isProjecteUUIDCheck()) ci.cancel();
    }
}
