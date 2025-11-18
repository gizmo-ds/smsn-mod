package dev.aika.smsn.forge.mixin.vazkii.zeta;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.aika.smsn.SMSN;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.violetmoon.zeta.module.ZetaModuleManager;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(value = ZetaModuleManager.class, remap = false)
public abstract class ZetaModuleManagerMixin {
    @WrapWithCondition(method = "constructAndSetup",
            at = @At(value = "INVOKE",
                    target = "Lorg/apache/logging/log4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;)V"
            ))
    private boolean spamLogs(Logger instance, String s, Object o) {
        return SMSN.CONFIG.isVazkiiModsSpamLogs();
    }
}
