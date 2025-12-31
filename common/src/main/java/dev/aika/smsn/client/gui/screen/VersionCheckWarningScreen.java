package dev.aika.smsn.client.gui.screen;

import dev.aika.smsn.SMSNPlatform;
import dev.aika.smsn.api.LoaderType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public final class VersionCheckWarningScreen extends WarningScreen {
    private static final LoaderType loader = LoaderType.getCurrentLoader();

    public VersionCheckWarningScreen(Screen parent) {
        super(
                parent,
                Component.translatable("gui.smsn.version_check_warning.title"),
                getMessage()
        );
    }

    private static Component getMessage() {
        if (loader == LoaderType.FABRIC) {
            return Component.translatable("gui.smsn.version_check_warning.modmenu_warn");
        } else if (loader == LoaderType.FORGE || loader == LoaderType.NEOFORGE) {
            return Component.translatable("gui.smsn.version_check_warning.fml_warn");
        } else return Component.empty();
    }

    @Override
    protected void addButtons(int y) {
        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.smsn.version_check_warning.disable_version_check"),
                b -> {
                    SMSNPlatform.setModVersionCheckEnabled(false);
                    Minecraft.getInstance().setScreen(this.parent);
                }
        ).bounds(this.width / 2 - 148 - 2, y, 148, 20).build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_PROCEED,
                b -> Minecraft.getInstance().setScreen(this.parent)
        ).bounds(this.width / 2 + 2, y, 148, 20).build());
    }
}
