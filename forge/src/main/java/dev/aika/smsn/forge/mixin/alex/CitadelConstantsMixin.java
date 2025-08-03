package dev.aika.smsn.forge.mixin.alex;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = com.github.alexthe666.citadel.CitadelConstants.class, remap = false)
public class CitadelConstantsMixin {
    @Inject(method = "isAprilFools", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onIsAprilFools(CallbackInfoReturnable<Boolean> cir) {
        if (!SMSN.CONFIG.isCitadelAprilFoolsContent()) cir.setReturnValue(false);
    }
}
