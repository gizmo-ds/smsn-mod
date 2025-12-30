package dev.aika.smsn.fabric.mixin.create_snr;

import com.railwayteam.railways.util.DevCapeUtils;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DevCapeUtils.class, remap = false)
public abstract class DevCapeUtilsMixin {
    @Shadow
    private boolean initialized;

    @Inject(method = "refresh", at = @At("HEAD"), cancellable = true)
    public void refresh(CallbackInfo ci) {
        if (SMSN.CONFIG.isCreateSnrDevCape()) return;
        this.initialized = true;
        ci.cancel();
    }
}
