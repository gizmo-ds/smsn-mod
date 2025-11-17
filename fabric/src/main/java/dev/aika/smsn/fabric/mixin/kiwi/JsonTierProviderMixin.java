package dev.aika.smsn.fabric.mixin.kiwi;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.kiwi.contributor.impl.JsonTierProvider;

import java.util.concurrent.CompletableFuture;

@Mixin(value = JsonTierProvider.class, remap = false)
public abstract class JsonTierProviderMixin {
    @Inject(method = "refresh", at = @At("HEAD"), cancellable = true)
    private void refresh(CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        if (!SMSN.CONFIG.isKiwiTier()) cir.setReturnValue(new CompletableFuture<>());
    }
}
