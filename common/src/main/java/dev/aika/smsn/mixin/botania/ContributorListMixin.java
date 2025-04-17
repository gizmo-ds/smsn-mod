package dev.aika.smsn.mixin.botania;


import dev.aika.smsn.SMSN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.handler.ContributorList;

@Mixin(value = ContributorList.class, remap = false)
public abstract class ContributorListMixin {
    @Inject(method = "fetch", at = @At("HEAD"), cancellable = true)
    private static void fetch(CallbackInfo ci) {
        if (!SMSN.CONFIG.isBotaniaContributorCheck()) ci.cancel();
    }
}
