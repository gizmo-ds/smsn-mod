package dev.aika.smsn.forge.mixin.vazkii.quark;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.aika.smsn.SMSN;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.violetmoon.quark.base.client.handler.ModelHandler;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(value = ModelHandler.class, remap = false)
public abstract class ModelHandlerMixin {
    @WrapWithCondition(method = "registerLayer",
            at = @At(value = "INVOKE",
                    target = "Lorg/apache/logging/log4j/Logger;info(Ljava/lang/String;)V"
            ))
    private static boolean spamLogs(Logger instance, String s) {
        return SMSN.CONFIG.isVazkiiModsSpamLogs();
    }
}
