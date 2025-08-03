package dev.aika.smsn.forge.mixin.alex;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;

@Mixin(value = com.github.alexthe666.citadel.web.WebHelper.class, remap = false)
public class CitadelWebHelperMixin {
    @Inject(method = "getURLContents", at = @At(value = "INVOKE", target = "Ljava/net/URL;openConnection()Ljava/net/URLConnection;"), cancellable = true, remap = false)
    private static void onGetURLContents(String urlString, String backupFileLoc, CallbackInfoReturnable<BufferedReader> cir) {
        if (!SMSN.CONFIG.isAlexModsContributorCheck()) cir.setReturnValue(null);
    }
}
