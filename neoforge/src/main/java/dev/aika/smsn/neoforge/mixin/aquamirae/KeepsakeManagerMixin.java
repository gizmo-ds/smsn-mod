package dev.aika.smsn.neoforge.mixin.aquamirae;

import com.google.gson.JsonElement;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = dev.obscuria.aquamirae.common.patreon.KeepsakeManager.class, remap = false)
public abstract class KeepsakeManagerMixin {
    @Inject(method = "fetchFromSourceWithTimeout", at = @At("HEAD"), cancellable = true)
    private static void fetchFromSourceWithTimeout(CallbackInfoReturnable<JsonElement> cir) {
        if (SMSN.CONFIG.isAquamiraeKeepsakeCheck()) return;
        cir.setReturnValue(null);
    }
}
