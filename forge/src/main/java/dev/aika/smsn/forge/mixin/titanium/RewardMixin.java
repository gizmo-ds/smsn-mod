package dev.aika.smsn.forge.mixin.titanium;

import com.hrznstudio.titanium.reward.Reward;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(value = Reward.class, remap = false)
public abstract class RewardMixin {
    @Inject(method = "getPlayers(Ljava/net/URL;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    private static void getPlayers(URL url, CallbackInfoReturnable<List<UUID>> cir) {
        if (!SMSN.CONFIG.isTitaniumReward()) cir.setReturnValue(new ArrayList<>());
    }
}
