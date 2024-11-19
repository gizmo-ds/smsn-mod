package dev.aika.smsn.forge.mixin.obscure;


import com.obscuria.obscureapi.network.ObscuriaCollection;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mixin(value = ObscuriaCollection.class, remap = false)
public abstract class ObscuriaCollectionMixin {
    @Inject(method = "upload", at = @At("HEAD"), cancellable = true)
    private static void upload(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "isLoaded", at = @At("HEAD"), cancellable = true)
    private static void isLoaded(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "isRunestone*", at = @At("HEAD"), cancellable = true)
    private static void isRunestone(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = "getCollectionMods", at = @At("HEAD"), cancellable = true)
    private static void getCollectionMods(CallbackInfoReturnable<List<ObscuriaCollection.Mod>> cir) {
        cir.setReturnValue(List.of());
    }

    @Inject(method = "getDescription", at = @At("HEAD"), cancellable = true)
    private static void getDescription(CallbackInfoReturnable<List<String>> cir) {
        cir.setReturnValue(List.of());
    }

    @Inject(method = "getDiscord", at = @At("HEAD"), cancellable = true)
    private static void getDiscord(CallbackInfoReturnable<List<String>> cir) {
        cir.setReturnValue(List.of());
    }

    @Inject(method = "getGitHub", at = @At("HEAD"), cancellable = true)
    private static void getGitHub(CallbackInfoReturnable<List<String>> cir) {
        cir.setReturnValue(List.of());
    }

    @Inject(method = "getPatreon", at = @At("HEAD"), cancellable = true)
    private static void getPatreon(CallbackInfoReturnable<List<String>> cir) {
        cir.setReturnValue(List.of());
    }

    @Mixin(value = ObscuriaCollection.Mod.class, remap = false)
    public static abstract class Mod {
        @Inject(method = "load", at = @At("HEAD"), cancellable = true)
        private static void load(String modID, CallbackInfoReturnable<ObscuriaCollection.Mod> cir) {
            cir.setReturnValue(null);
        }
    }
}
