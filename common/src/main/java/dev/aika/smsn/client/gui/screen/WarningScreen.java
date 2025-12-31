package dev.aika.smsn.client.gui.screen;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public abstract class WarningScreen extends Screen {
    protected final Screen parent;
    protected final Component message;
    protected int messageHeight;

    protected WarningScreen(Screen parent, Component title, Component message) {
        super(title);

        this.parent = parent;
        this.message = message;
    }

    @Override
    protected void init() {
        super.init();

        this.messageHeight = this.font.wordWrapHeight(this.message, 300);

        this.addButtons(height / 2 + this.messageHeight / 2 + 15);
    }

    protected void addButtons(int y) {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_PROCEED,
                b -> Minecraft.getInstance().setScreen(this.parent)
        ).bounds(this.width / 2 - 200 / 2, y, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title.copy().withStyle(ChatFormatting.BOLD),
                this.width / 2, this.height / 2 - this.messageHeight / 2 - this.font.lineHeight - 15,
                0xFFAA00);
        MultiLineLabel.create(this.font, this.message, 300).renderCentered(guiGraphics,
                this.width / 2, this.height / 2 - this.messageHeight / 2);
    }
}
