package dev.aika.smsn.forge.mixin.alex;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;

@Mixin(value = com.github.alexmodguy.alexscaves.server.misc.WebHelper.class, remap = false)
public class AlexsCavesWebHelperMixin {
    @Inject(method = "getURLContents", at = @At(value = "INVOKE", target = "Ljava/net/URL;openConnection()Ljava/net/URLConnection;"), cancellable = true, remap = false)
    private static void onGetURLContents(String urlString, String backupFileLoc, CallbackInfoReturnable<BufferedReader> cir) {
        cir.setReturnValue(null);
        cir.cancel();
    }
}
