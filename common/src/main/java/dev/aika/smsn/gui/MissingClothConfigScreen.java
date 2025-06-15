package dev.aika.smsn.gui;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

public class MissingClothConfigScreen extends Screen {
    private final static String CLOTH_CONFIG_MODRINTH = "https://modrinth.com/mod/9s6osm5g";
    private final static String CLOTH_CONFIG_CURSEFORGE = "https://www.curseforge.com/minecraft/mc-mods/cloth-config";

    private final Screen parent;
    private MultiLineLabel message = MultiLineLabel.EMPTY;

    public MissingClothConfigScreen(Screen parent) {
        super(Component.translatable("gui.smsn.missing_cloth_config.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        var posX = (this.width - 200) / 2;
        var posY = this.height / 2;

        this.message = MultiLineLabel.create(this.font, Component.translatable("gui.smsn.missing_cloth_config.message"), 300);
        this.addRenderableWidget(Button.builder(Component.translatable("gui.smsn.missing_cloth_config.modrinth_download"),
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_MODRINTH)).bounds(posX, posY, 200, 20).build());
        posY += 25;
        this.addRenderableWidget(Button.builder(Component.translatable("gui.smsn.missing_cloth_config.curseforge_download"),
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_CURSEFORGE)).bounds(posX, posY, 200, 20).build());
        posY += 25;
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_BACK,
                (pressed) -> Minecraft.getInstance().setScreen(this.parent)).bounds(posX, posY, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(font, title, width / 2, this.height / 2 - 60, CommonColors.WHITE);
        this.message.renderCentered(graphics, this.width / 2, this.height / 2 - 40);
    }
}
