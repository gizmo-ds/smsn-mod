package dev.aika.smsn.fabric.mixin.create_tram_additions;

import dev.aika.smsn.SMSN;
import hu.qliqs.TramAdditions;
import hu.qliqs.state.JsonMapStorage;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TramAdditions.class, remap = false)
public abstract class TramAdditionsMixin {
    @Shadow
    public static JsonMapStorage jsonMapStorage;

    @Inject(
            method = "lambda$init$0",
            at = @At(value = "INVOKE", target = "Lhu/qliqs/WsClient;<init>(Ljava/net/URI;)V"),
            cancellable = true
    )
    @SuppressWarnings("SpellCheckingInspection")
    private static void init(MinecraftServer server, CallbackInfo ci) {
        if (SMSN.CONFIG.isCreateTramAdditionsTTL()) return;
        ci.cancel();
        jsonMapStorage.load(server.overworld());
    }

    @Inject(
            method = "lambda$init$1",
            at = @At(value = "INVOKE", target = "Lhu/qliqs/WsClient;close()V"),
            cancellable = true
    )
    @SuppressWarnings("SpellCheckingInspection")
    private static void close(MinecraftServer server, CallbackInfo ci) {
        if (SMSN.CONFIG.isCreateTramAdditionsTTL()) return;
        ci.cancel();
        jsonMapStorage.save();
    }
}
