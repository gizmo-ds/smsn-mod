package dev.aika.smsn.forge.mixin.unionlib;

import com.stereowalker.unionlib.supporter.Supporters;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(value = Supporters.class, remap = false)
public abstract class SupportersMixin {
    @Inject(method = "populateSupporters", at = @At("HEAD"), cancellable = true)
    private static void populateSupporters(File cache, boolean createCache, CallbackInfo ci) {
        if (!SMSN.CONFIG.isUnionLibSupporterCheck()) ci.cancel();
    }
}
