package dev.aika.smsn.neoforge.mixin.aquamirae;

import dev.aika.smsn.SMSN;
import dev.obscuria.aquamirae.common.patreon.KeepsakeRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings({"MixinAnnotationTarget", "RedundantSuppression"})
@Mixin(value = dev.obscuria.aquamirae.common.patreon.KeepsakeManager.class, remap = false)
public abstract class KeepsakeManagerMixin_726 {
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "loadRepositoryAsync", at = @At("HEAD"), cancellable = true)
    private static void loadRepositoryAsync(CallbackInfoReturnable<CompletableFuture<KeepsakeRepository>> cir) {
        if (SMSN.CONFIG.isAquamiraeKeepsakeCheck()) return;
        cir.setReturnValue(CompletableFuture.completedFuture(KeepsakeRepository.EMPTY));
    }
}