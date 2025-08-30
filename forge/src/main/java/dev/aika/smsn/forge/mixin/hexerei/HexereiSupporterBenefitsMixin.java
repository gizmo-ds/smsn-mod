package dev.aika.smsn.forge.mixin.hexerei;

import dev.aika.smsn.SMSN;
import net.joefoxe.hexerei.util.HexereiSupporterBenefits;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.URL;

@Mixin(value = HexereiSupporterBenefits.class, remap = false)
public abstract class HexereiSupporterBenefitsMixin {
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void init(CallbackInfo ci) {
        if (!SMSN.CONFIG.isHexereiSupporter()) ci.cancel();
    }

    @Inject(method = "readUrl", at = @At("HEAD"), cancellable = true)
    private static void readUrl(URL url, CallbackInfoReturnable<String> cir) {
        if (!SMSN.CONFIG.isHexereiSupporter()) cir.setReturnValue("");
    }
}
