package dev.aika.smsn.neoforge.mixin.ribbits;

import com.yungnickyoung.minecraft.ribbits.client.supporters.SupportersJSON;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SupportersJSON.class, remap = false)
public abstract class SupportersJSONMixin {
    @Inject(method = "populateSupportersList", at = @At("HEAD"), cancellable = true)
    private static void populateSupportersList(CallbackInfo ci) {
        if (!SMSN.CONFIG.isRibbitsSupporterHat()) ci.cancel();
    }
}
