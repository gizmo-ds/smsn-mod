package dev.aika.smsn.forge.mixin.enigmatic_legacy;

import com.integral.enigmaticlegacy.handlers.EnigmaticUpdateHandler;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EnigmaticUpdateHandler.class, remap = false)
public abstract class EnigmaticUpdateHandlerMixin {
    @Shadow
    private static String newestVersion;

    @Shadow
    private static String currentVersion;

    @Inject(method = "getNewestVersion", at = @At("HEAD"), cancellable = true)
    private static void getNewestVersion(CallbackInfo ci) {
        if (SMSN.CONFIG.isEnigmaticLegacyUpdateCheck()) return;
        ci.cancel();
        newestVersion = currentVersion;
    }
}
