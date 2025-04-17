package dev.aika.smsn.mixin.supplementaries;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.mehvahdjukaar.supplementaries.Supplementaries.class, remap = false)
public abstract class SupplementariesMixin {
    @Inject(method = "commonInit", at = @At(value = "INVOKE", target = "Lnet/mehvahdjukaar/supplementaries/common/utils/Credits;fetchFromServer()V"), cancellable = true)
    private static void fetchFromServer(CallbackInfo ci) {
        if (!SMSN.CONFIG.isSupplementariesCreditsCheck()) ci.cancel();
    }
}
