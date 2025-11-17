package dev.aika.smsn.fabric.mixin.majrusz;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(targets = "com.majruszlibrary.modhelper.VersionChecker", remap = false)
public abstract class VersionCheckerMixin {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private void register(CallbackInfo ci) {
        if (!SMSN.CONFIG.isMajruszModsVersionCheck()) ci.cancel();
    }
}
