package dev.aika.smsn.fabric.mixin.fancytoasts;

import net.bivrik.fancytoasts.core.manager.CreditsManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CreditsManager.class, remap = false)
public abstract class CreditsManagerMixin {
    @Shadow
    private CreditsManager.CreditsData cachedCredits;

    @Shadow
    protected abstract CreditsManager.CreditsData readCredits();

    @Shadow
    public abstract CreditsManager.CreditsData getFallback();

    @Inject(method = "onModInit", at = @At("HEAD"), cancellable = true)
    public void onModInit(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "getCredits", at = @At("HEAD"))
    public void getCredits(CallbackInfoReturnable<CreditsManager.CreditsData> cir) {
        if (this.cachedCredits != null) return;
        this.cachedCredits = this.readCredits();
        if (this.cachedCredits == null) this.cachedCredits = this.getFallback();
    }
}
