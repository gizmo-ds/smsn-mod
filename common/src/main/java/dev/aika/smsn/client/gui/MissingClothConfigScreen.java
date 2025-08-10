package dev.aika.smsn.client.gui;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

public class MissingClothConfigScreen extends Screen {
    private final static String CLOTH_CONFIG_MODRINTH = "https://modrinth.com/mod/9s6osm5g";
    private final static String CLOTH_CONFIG_CURSEFORGE = "https://www.curseforge.com/minecraft/mc-mods/cloth-config";

    private final Component message = Component.translatable("gui.smsn.missing_cloth_config.message");
    private final Component curseforgeButton = Component.translatable("gui.smsn.missing_cloth_config.curseforge_download");
    private final Component modrinthButton = Component.translatable("gui.smsn.missing_cloth_config.modrinth_download");

    private final Screen parent;

    public MissingClothConfigScreen(Screen parent) {
        super(Component.translatable("gui.smsn.missing_cloth_config.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        int posX = (width - 200) / 2;
        int posY = height / 2 - 20;

        posY += font.wordWrapHeight(message, 300);

        addRenderableWidget(Button.builder(curseforgeButton, openLink(CLOTH_CONFIG_CURSEFORGE))
                .bounds(posX, posY, 100, 20).build());
        addRenderableWidget(Button.builder(modrinthButton, openLink(CLOTH_CONFIG_MODRINTH))
                .bounds(posX + 100, posY, 100, 20).build());
        addRenderableWidget(Button.builder(CommonComponents.GUI_BACK, this::onBack)
                .bounds(posX, posY + 25, 200, 20).build());
    }

    private void onBack(Button ignoredButton) {
        Minecraft.getInstance().setScreen(parent);
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

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(font, title, width / 2, height / 2 - 50, CommonColors.WHITE);
        MultiLineLabel.create(font, message, 300).renderCentered(graphics, width / 2, height / 2 - 30);
    }
}
