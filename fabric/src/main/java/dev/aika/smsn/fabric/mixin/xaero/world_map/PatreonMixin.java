package dev.aika.smsn.fabric.mixin.xaero.world_map;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = xaero.map.patreon.Patreon.class, remap = false)
public class PatreonMixin {
    @Inject(method = "checkPatreon", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onCheckPatreon(CallbackInfo ci) {
        if (!SMSN.CONFIG.isXaeroMapPatreonCheck()) ci.cancel();
    }
}
