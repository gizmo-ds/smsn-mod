package dev.aika.smsn.mixin.client;

import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import dev.aika.smsn.SMSN;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.TitleScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {
    @Shadow
    @Nullable
    private RealmsNotificationsScreen realmsNotificationsScreen;

    @Inject(method = "realmsNotificationsEnabled", at = @At("HEAD"))
    public void realmsNotificationsEnabled(CallbackInfoReturnable<Boolean> cir) {
        if (SMSN.CONFIG.isHideRealmsButton()) this.realmsNotificationsScreen = null;
    }

    @ModifyVariable(method = "init", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/TitleScreen;createNormalMenuOptions(II)I"),
            ordinal = 3)
    public int fixButtonsPos(int value) {
        return SMSN.CONFIG.isHideRealmsButton() ? value - 24 : value;
    }

    @Redirect(method = "createNormalMenuOptions", at = @At(value = "INVOKE", ordinal = 2,
            target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"))
    public GuiEventListener createNormalMenuOptions(TitleScreen instance, GuiEventListener guiEventListener) {
        if (SMSN.CONFIG.isHideRealmsButton() && guiEventListener instanceof Button button) {
            button.visible = false;
            button.setFocused(false);
        }
        return ((ScreenInvoker) instance).invokeAddRenderableWidget(guiEventListener);
    }
}
