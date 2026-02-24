package dev.aika.smsn.forge.mixin.illagerrevolution;

import dev.aika.smsn.SMSN;
import net.BKTeam.illagerrevolutionmod.IllagerRevolutionMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;
import java.net.HttpURLConnection;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(value = IllagerRevolutionMod.class, remap = false)
public abstract class IllagerRevolutionModMixin {
    @Redirect(method = "<init>", at = @At(
            value = "INVOKE",
            target = "Ljava/net/HttpURLConnection;setRequestMethod(Ljava/lang/String;)V"
    ))
    public void injected(HttpURLConnection instance, String i) throws IOException {
        if (IllagerRevolutionMod.ACOLYTES_SKIN_UUID == null) IllagerRevolutionMod.ACOLYTES_SKIN_UUID = "";
        if (IllagerRevolutionMod.MAGES_SKIN_UUID == null) IllagerRevolutionMod.MAGES_SKIN_UUID = "";
        if (IllagerRevolutionMod.KNIGHTS_SKIN_UUID == null) IllagerRevolutionMod.KNIGHTS_SKIN_UUID = "";

        if (!SMSN.CONFIG.isIllagerRevolutionPatreon()) throw new IOException();
        else instance.setRequestMethod("GET");
    }
}
