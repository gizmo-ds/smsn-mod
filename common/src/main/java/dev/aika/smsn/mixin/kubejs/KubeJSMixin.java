package dev.aika.smsn.mixin.kubejs;

import dev.latvian.mods.kubejs.KubeJS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = KubeJS.class, remap = false)
public abstract class KubeJSMixin {
    @Inject(method = "loadComplete",
            at = @At(value = "INVOKE", target = "Ljava/lang/Thread;start()V"),
            cancellable = true)
    private void loadComplete(CallbackInfo ci) {
        ci.cancel();
    }
}
