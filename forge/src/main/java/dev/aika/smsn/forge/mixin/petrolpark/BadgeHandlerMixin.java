package dev.aika.smsn.forge.mixin.petrolpark;

import dev.aika.smsn.SMSN;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import com.petrolpark.badge.BadgeHandler;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BadgeHandler.class, remap = false)
public abstract class BadgeHandlerMixin {
    @Inject(method = "fetchAndAddBadgesIncludingEarlyBird", at = @At("HEAD"), cancellable = true)
    private static void fetchAndAddBadgesIncludingEarlyBird(ServerPlayer player, CallbackInfo ci) {
        if (!SMSN.CONFIG.isPetrolparkBadgeCheck()) ci.cancel();
    }
}
