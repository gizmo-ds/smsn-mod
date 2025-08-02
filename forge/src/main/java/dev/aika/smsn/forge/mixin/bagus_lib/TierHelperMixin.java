package dev.aika.smsn.forge.mixin.bagus_lib;

import bagu_chan.bagus_lib.util.TierHelper;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.StringReader;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(value = TierHelper.class, remap = false)
public abstract class TierHelperMixin {
    @Inject(method = "getSuporterContents", at = @At(value = "INVOKE", target = "Ljava/net/URL;openConnection()Ljava/net/URLConnection;"), cancellable = true)
    private static void onGetSuporterContents(CallbackInfoReturnable<BufferedReader> cir) {
        if (!SMSN.CONFIG.isBagusLibSupportersCheck())
            cir.setReturnValue(new BufferedReader(new StringReader("")));
    }
}
