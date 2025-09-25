package dev.aika.smsn.forge.mixin.create_tram_additions;

import dev.aika.smsn.SMSN;
import hu.qliqs.WorldTick;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldTick.class, remap = false)
public abstract class WorldTickMixin {
    @Inject(method = "onWorldTick",
            at = @At(value = "INVOKE", target = "Lhu/qliqs/WsClient;send(Ljava/lang/String;)V"),
            cancellable = true
    )
    @SuppressWarnings("SpellCheckingInspection")
    private static void onWorldTick(MinecraftServer server, CallbackInfo ci) {
        if (SMSN.CONFIG.isCreateTramAdditionsTTS()) return;
        ci.cancel();
    }
}
