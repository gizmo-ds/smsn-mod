package dev.aika.smsn.neoforge.mixin.violetmoon.quark;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.violetmoon.quark.base.client.config.QButton;

import java.time.Month;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(value = QButton.class, remap = false)
public abstract class QButtonMixin {
    @Inject(method = "celebrate(Ljava/lang/String;IILjava/time/Month;I)V", at = @At("HEAD"), cancellable = true)
    private static void celebrate(String name, int day, int end, Month month, int tier, CallbackInfo ci) {
        if (SMSN.CONFIG.quarkCelebration.isHide(name)) ci.cancel();
    }
}
