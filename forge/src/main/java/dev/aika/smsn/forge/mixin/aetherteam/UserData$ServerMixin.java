package dev.aika.smsn.forge.mixin.aetherteam;


import com.aetherteam.nitrogen.api.users.UserData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = UserData.Server.class, remap = false)
public abstract class UserData$ServerMixin {
    @Inject(method = "sendUserRequest", at = @At("HEAD"), cancellable = true)
    private static void sendUserRequest(MinecraftServer server, ServerPlayer serverPlayer, UUID uuid, CallbackInfo ci) {
        ci.cancel();
    }
}
