package dev.aika.smsn.fabric.mixin.unionlib;

import com.google.common.collect.ImmutableMap;
import com.stereowalker.unionlib.supporter.Cosmetic;
import com.stereowalker.unionlib.supporter.CosmeticSelection;
import com.stereowalker.unionlib.supporter.Cosmetics;
import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.util.Map;
import java.util.UUID;

@Mixin(value = Cosmetics.class, remap = false)
public abstract class CosmeticsMixin {
    @Shadow
    private static Map<String, Cosmetic> ALL_COSMETICS;

    @Shadow
    protected static Cosmetic NONE;

    @Inject(method = "obtainAllCosmetics", at = @At("HEAD"), cancellable = true)
    private static void obtainAllCosmetics(File cache, File variantCache, File segmentCache, boolean createCache, CallbackInfo ci) {
        if (SMSN.CONFIG.isUnionLibCosmeticsFeature()) return;
        if (ALL_COSMETICS.isEmpty()) ALL_COSMETICS = ImmutableMap.<String, Cosmetic>builder().put("", NONE).build();
        ci.cancel();
    }

    @Inject(method = "onConnectToCosmeticDB", at = @At("HEAD"), cancellable = true)
    private static void onConnectToCosmeticDB(Runnable run, CallbackInfo ci) {
        if (!SMSN.CONFIG.isUnionLibCosmeticsFeature()) ci.cancel();
    }

    @Inject(method = "fetchOwnedCosmetics", at = @At("HEAD"), cancellable = true)
    private static void fetchOwnedCosmetics(UUID player, Runnable onFetched, CallbackInfo ci) {
        if (!SMSN.CONFIG.isUnionLibCosmeticsFeature()) ci.cancel();
    }

    @Inject(method = "pollCosmetics", at = @At("HEAD"), cancellable = true)
    private static void pollCosmetics(CallbackInfo ci) {
        if (!SMSN.CONFIG.isUnionLibCosmeticsFeature()) ci.cancel();
    }

    @Inject(method = "obtainCosmetics", at = @At("HEAD"), cancellable = true)
    private static void obtainCosmetics(File cache, boolean createCache, CallbackInfo ci) {
        if (!SMSN.CONFIG.isUnionLibCosmeticsFeature()) ci.cancel();
    }

    @Inject(method = "updateCosmetic", at = @At("HEAD"), cancellable = true)
    private static void updateCosmetic(UUID player, CosmeticSelection newHeadwear, Runnable onComplete, CallbackInfo ci) {
        if (!SMSN.CONFIG.isUnionLibCosmeticsFeature()) ci.cancel();
    }

    @Inject(method = "updateCosmeticColors", at = @At("HEAD"), cancellable = true)
    private static void updateCosmeticColors(UUID player, CosmeticSelection newHeadwear, CallbackInfoReturnable<Boolean> cir) {
        if (!SMSN.CONFIG.isUnionLibCosmeticsFeature()) cir.setReturnValue(false);
    }
}
