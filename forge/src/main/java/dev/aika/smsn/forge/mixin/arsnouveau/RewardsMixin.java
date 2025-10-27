package dev.aika.smsn.forge.mixin.arsnouveau;

import com.hollingsworth.arsnouveau.setup.reward.Rewards;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Rewards.class, remap = false)
public abstract class RewardsMixin {
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void init(CallbackInfo ci) {
        if (!SMSN.CONFIG.isArsnouveauRewards()) ci.cancel();
    }
}
