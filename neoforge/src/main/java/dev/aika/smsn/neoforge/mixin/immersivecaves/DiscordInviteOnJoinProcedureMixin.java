package dev.aika.smsn.neoforge.mixin.immersivecaves;

import dev.aika.smsn.SMSN;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.mcreator.immersivecaves.procedures.DiscordInviteOnJoinProcedure.class, remap = false)
public abstract class DiscordInviteOnJoinProcedureMixin {
    @Inject(method = "execute(Lnet/neoforged/bus/api/Event;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private static void execute(Event event, LevelAccessor world, Entity entity, CallbackInfo ci) {
        if (!SMSN.CONFIG.isImmersiveCavesDiscordMessage()) ci.cancel();
    }
}
