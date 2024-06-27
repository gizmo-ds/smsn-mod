package dev.aika.smsn.forge.mixin.quark;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "org.violetmoon.quark.base.handler.ContributorRewardHandler$ThreadContributorListLoader", remap = false)
public class ThreadContributorListLoaderMixin {
    @Inject(method = "run", at = @At("HEAD"), cancellable = true, remap = false)
    private void onRun(CallbackInfo ci) {
        ci.cancel();
    }
}
