package dev.aika.smsn.fabric.mixin.ribbits;

import com.yungnickyoung.minecraft.ribbits.entity.RibbitEntity;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RibbitEntity.class, remap = false)
public abstract class RibbitEntityMixin {
    @Inject(method = "isPrideMonth", at = @At("HEAD"), cancellable = true)
    private static void isPrideMonth(CallbackInfoReturnable<Boolean> cir) {
        if (!SMSN.CONFIG.isRibbitsNoMorePrideMonth()) cir.setReturnValue(false);
    }
}
