package dev.aika.smsn.neoforge.mixin.kubejs;

import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = dev.latvian.mods.kubejs.KubeJSModEventHandler.class, remap = false)
public abstract class KubeJSModEventHandlerMixin {
    @Inject(method = "loadComplete0",
            at = @At(value = "INVOKE", target = "Ljava/util/concurrent/ExecutorService;submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;"),
            cancellable = true)
    private static void updateCheck(CallbackInfo ci) {
        if (!SMSN.CONFIG.kubejsUpdateCheck()) ci.cancel();
    }
}
