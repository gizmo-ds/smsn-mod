package dev.aika.smsn.forge.mixin.enigmatic_legacy;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.UUID;

@Mixin(value = com.aizistral.enigmaticlegacy.handlers.DevotedBelieversHandler.class, remap = false)
public abstract class DevotedBelieversHandlerMixin {
    @Inject(method = "loadDevotedBelievers", at = @At("HEAD"), cancellable = true)
    private static void loadDevotedBelievers(CallbackInfoReturnable<Map<String, UUID>> cir) {
        if (!SMSN.CONFIG.isEnigmaticLegacyFetchDevotedBelievers()) cir.setReturnValue(Map.of());
    }
}
