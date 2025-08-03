package dev.aika.smsn.fabric.mixin.exposure;

import dev.aika.smsn.SMSN;
import io.github.mortuusars.exposure.util.supporter.Patreon;
import io.github.mortuusars.exposure.util.supporter.Supporter;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Mixin(value = Patreon.class, remap = false)
public abstract class PatreonMixin {
    @Shadow
    private @Nullable Map<Patreon.Tier, List<Supporter>> patrons;

    @Inject(method = "query", at = @At("HEAD"), cancellable = true)
    public void query(CallbackInfoReturnable<Map<Patreon.Tier, List<Supporter>>> cir) {
        if (SMSN.CONFIG.isExposureGoldenCameraSkin()) return;
        patrons = Collections.emptyMap();
        cir.setReturnValue(patrons);
    }
}
