package dev.aika.smsn.forge.mixin.quark;

import dev.aika.smsn.SMSN;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.base.client.config.screen.widgets.QButton;

import java.time.Month;

@OnlyIn(Dist.CLIENT)
@Mixin(value = QButton.class, remap = false)
public abstract class QButtonMixin {
    @Inject(method = "celebrate(Ljava/lang/String;IILjava/time/Month;I)V", at = @At("HEAD"), cancellable = true)
    private static void celebrate(String name, int day, int end, Month month, int tier, CallbackInfo ci) {
        if (SMSN.CONFIG.quarkCelebration.isHide(name)) ci.cancel();
    }
}
