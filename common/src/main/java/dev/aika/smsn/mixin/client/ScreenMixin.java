package dev.aika.smsn.mixin.client;

import dev.aika.smsn.event.SMSNClientEvents;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Inject(method = "rebuildWidgets", at = @At("HEAD"))
    private void rebuildWidgets(CallbackInfo ci) {
        if (this.getClass().getName().equals(TitleScreen.class.getName())) {
            SMSNClientEvents.versionCheckWarn((Screen) (Object) this);
        }
    }
}
