package dev.aika.smsn.client.gui.screen;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public final class MissingClothConfigScreen extends WarningScreen {
    private final static String CLOTH_CONFIG_MODRINTH = "https://modrinth.com/mod/9s6osm5g";
    private final static String CLOTH_CONFIG_CURSEFORGE = "https://www.curseforge.com/minecraft/mc-mods/cloth-config";

    public MissingClothConfigScreen(Screen parent) {
        super(
                parent,
                Component.translatable("gui.smsn.missing_cloth_config.title"),
                Component.translatable("gui.smsn.missing_cloth_config.message")
        );
    }

    @Override
    protected void addButtons(int y) {
        this.addRenderableWidget(Button.builder(
                        Component.translatable("gui.smsn.missing_cloth_config.curseforge_download"),
                        openLink(CLOTH_CONFIG_CURSEFORGE))
                .bounds(this.width / 2 - 148 - 2, y, 148, 20).build());
        this.addRenderableWidget(Button.builder(
                        Component.translatable("gui.smsn.missing_cloth_config.modrinth_download"),
                        openLink(CLOTH_CONFIG_MODRINTH))
                .bounds(this.width / 2 + 2, y, 148, 20).build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_BACK,
                b -> Minecraft.getInstance().setScreen(this.parent)
        ).bounds(this.width / 2 - 300 / 2, y + 24, 300, 20).build());
    }

    private Button.OnPress openLink(String link) {
        return b -> {
            if (minecraft == null) return;
            minecraft.setScreen(new ConfirmLinkScreen((v) -> {
                if (v) Util.getPlatform().openUri(link);
                minecraft.setScreen(this);
            }, link, true));
        };
    }
}
