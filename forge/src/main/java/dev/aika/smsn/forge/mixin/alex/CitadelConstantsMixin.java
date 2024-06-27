package dev.aika.smsn.forge.mixin.alex;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = com.github.alexthe666.citadel.CitadelConstants.class, remap = false)
public class CitadelConstantsMixin {
    @Inject(method = "isAprilFools", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onIsAprilFools(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
        cir.cancel();
    }
}
