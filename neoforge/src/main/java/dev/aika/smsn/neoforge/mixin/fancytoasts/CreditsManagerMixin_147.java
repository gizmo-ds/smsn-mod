package dev.aika.smsn.neoforge.mixin.fancytoasts;

import net.bivrik.fancytoasts.core.manager.CreditsManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings({"MixinAnnotationTarget", "RedundantSuppression"})
@Mixin(value = CreditsManager.class, remap = false)
public abstract class CreditsManagerMixin_147 {
    @Shadow
    private CreditsManager.CreditsData credits;

    @Shadow
    protected abstract CompletableFuture<CreditsManager.CreditsData> loadAndCombineCreditsAsync();

    @Inject(method = "onModInit", at = @At("HEAD"), cancellable = true)
    public void onModInit(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "getCredits", at = @At("HEAD"))
    public void getCredits(CallbackInfoReturnable<CreditsManager.CreditsData> cir) {
        if (this.credits != null) return;
        try {
            this.credits = this.loadAndCombineCreditsAsync().get();
        } catch (Exception ignored) {
        }
    }
}
