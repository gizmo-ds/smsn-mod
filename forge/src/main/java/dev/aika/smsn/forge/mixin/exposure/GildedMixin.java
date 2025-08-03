package dev.aika.smsn.forge.mixin.exposure;

import dev.aika.smsn.SMSN;
import io.github.mortuusars.exposure.util.supporter.Gilded;
import io.github.mortuusars.exposure.util.supporter.Supporter;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;

@Mixin(value = Gilded.class, remap = false)
public abstract class GildedMixin {
    @Shadow
    private @Nullable List<Supporter> gildedSupporters;

    @Inject(method = "query", at = @At("HEAD"), cancellable = true)
    public void query(CallbackInfoReturnable<List<Supporter>> cir) {
        if (SMSN.CONFIG.isExposureGoldenCameraSkin()) return;
        gildedSupporters = Collections.emptyList();
        cir.setReturnValue(gildedSupporters);
    }
}
