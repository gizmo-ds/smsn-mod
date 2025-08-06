package dev.aika.smsn.forge.mixin.alex;

import com.github.alexthe666.citadel.web.WebHelper;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Mixin(value = com.github.alexmodguy.alexscaves.server.misc.WebHelper.class, remap = false)
public class AlexsCavesWebHelperMixin {
    @Inject(method = "getURLContents", at = @At(value = "INVOKE", target = "Ljava/net/URL;openConnection()Ljava/net/URLConnection;"), cancellable = true, remap = false)
    private static void onGetURLContents(String urlString, String backupFileLoc, CallbackInfoReturnable<BufferedReader> cir) {
        if (SMSN.CONFIG.isAlexModsContributorCheck()) return;
        BufferedReader result = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(WebHelper.class.getClassLoader().getResourceAsStream(backupFileLoc)),
                StandardCharsets.UTF_8
        ));
        cir.setReturnValue(result);
    }
}
