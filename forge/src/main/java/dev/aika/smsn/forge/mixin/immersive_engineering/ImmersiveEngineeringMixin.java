package dev.aika.smsn.forge.mixin.immersive_engineering;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = blusunrize.immersiveengineering.ImmersiveEngineering.ThreadContributorSpecialsDownloader.class, remap = false)
public abstract class ImmersiveEngineeringMixin {
    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    public void run(CallbackInfo ci) {
        if (!SMSN.CONFIG.isImmersiveEngineeringSpecialRevolvers()) ci.cancel();
    }
}
