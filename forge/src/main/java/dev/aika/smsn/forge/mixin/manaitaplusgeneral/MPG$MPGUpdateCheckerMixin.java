package dev.aika.smsn.forge.mixin.manaitaplusgeneral;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "github.com.gengyoubo.MPG.MPG$MPGUpdateChecker", remap = false)
public abstract class MPG$MPGUpdateCheckerMixin {
    @Inject(method = "loadLatestVersion", at = @At("HEAD"), cancellable = true)
    private static void loadLatestVersion(CallbackInfoReturnable<String> cir) {
        if (!SMSN.CONFIG.isMpgUpdateChecker()) cir.setReturnValue(null);
    }
}