package dev.aika.smsn.forge.mixin.ba;

import dev.aika.smsn.SMSN;
import net.wzz.bluearchivescraft.BlueArchivescraftMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BlueArchivescraftMod.class, remap = false)
public abstract class BlueArchivescraftModMixin {
    @Inject(method = "checkForUpdates", at = @At("HEAD"), cancellable = true)
    private void checkForUpdates(CallbackInfo ci) {
        if (!SMSN.CONFIG.isBaCheckUpdate()) ci.cancel();
    }
}
