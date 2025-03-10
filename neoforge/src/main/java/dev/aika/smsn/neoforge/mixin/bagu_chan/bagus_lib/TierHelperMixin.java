package dev.aika.smsn.neoforge.mixin.bagu_chan.bagus_lib;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;

@Mixin(value = bagu_chan.bagus_lib.util.reward.TierHelper.class, remap = false)
public abstract class TierHelperMixin {
    @Inject(method = "getSuporterContents", at = @At(value = "INVOKE", target = "Ljava/net/URL;openConnection()Ljava/net/URLConnection;"), cancellable = true)
    private static void onGetSuporterContents(CallbackInfoReturnable<BufferedReader> cir) {
        if (!SMSN.CONFIG.bagusLibSupportersCheck())
            cir.setReturnValue(null);
    }
}
