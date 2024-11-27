package dev.aika.smsn.mixin.supplementaries;

import dev.aika.smsn.SMSN;
import net.mehvahdjukaar.supplementaries.common.utils.Credits;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Credits.class, remap = false)
public abstract class CreditsMixin {
    @Inject(method = "fetchFromServer", at = @At("HEAD"), cancellable = true)
    private static void fetchFromServer(CallbackInfo ci) {
        if (!SMSN.CONFIG.supplementariesCreditsCheck()) ci.cancel();
    }
}
