package dev.aika.smsn.fabric.mixin.kiwi;

import com.google.common.collect.ImmutableSet;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.kiwi.contributor.impl.KiwiTierProvider;

import java.util.Set;

@Mixin(value = KiwiTierProvider.class, remap = false)
public abstract class KiwiTierProviderMixin {
    @Inject(method = "getPlayerTiers", at = @At("HEAD"), cancellable = true)
    private void getPlayerTiers(String playerName, CallbackInfoReturnable<Set<String>> cir) {
        if (!SMSN.CONFIG.isKiwiTier()) cir.setReturnValue(ImmutableSet.of());
    }
}
