package dev.aika.smsn.forge.mixin.tenshilib;

import dev.aika.smsn.SMSN;
import io.github.flemmli97.tenshilib.patreon.PatreonDataManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = PatreonDataManager.class, remap = false)
public abstract class PatreonDataManagerMixin {
    @Shadow
    private static Map<String, PatreonDataManager.PatreonPlayerInfo> PLAYERS;

    @Shadow
    private static boolean READING;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void init(CallbackInfo ci) {
        if (SMSN.CONFIG.isTenshilibPatreon()) return;
        if (PLAYERS == null) {
            PLAYERS = new HashMap<>();
            READING = false;
        }
        ci.cancel();
    }
}
