package dev.aika.smsn.forge.mixin.xxrexraptorxx.allthecompatibility;

import dev.aika.smsn.SMSN;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xxrexraptorxx.allthecompatibility.utils.Events;

import java.net.URL;

@Mixin(value = Events.class, remap = false)
public abstract class EventsMixin {
    @Inject(method = "SupporterCheck", at = @At("HEAD"), cancellable = true)
    private static void SupporterCheck(URL url, Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!SMSN.CONFIG.isXxRexRaptorxXModsSupporterCheck()) cir.setReturnValue(false);
    }

    @Inject(method = "SupporterRewards", at = @At("HEAD"), cancellable = true)
    private static void SupporterRewards(PlayerEvent.PlayerLoggedInEvent event, CallbackInfo ci) {
        if (!SMSN.CONFIG.isXxRexRaptorxXModsSupporterCheck()) ci.cancel();
    }
}
