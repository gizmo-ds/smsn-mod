package dev.aika.smsn.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TranslatableComponent;

public class MissingClothConfigScreen extends Screen {
    private final static String CLOTH_CONFIG_MODRINTH = "https://modrinth.com/mod/9s6osm5g";
    private final static String CLOTH_CONFIG_CURSEFORGE = "https://www.curseforge.com/minecraft/mc-mods/cloth-config";

    private final Screen parent;
    private final TranslatableComponent messageComponent = new TranslatableComponent("gui.smsn.missing_cloth_config.message");

    public MissingClothConfigScreen(Screen parent) {
        super(new TranslatableComponent("gui.smsn.missing_cloth_config.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        var posX = (this.width - 200) / 2;
        var posY = this.height / 2 - 20;
        posY += this.font.wordWrapHeight(messageComponent.getString(), 300);
        this.addRenderableWidget(new Button(posX, posY, 100, 20,
                new TranslatableComponent("gui.smsn.missing_cloth_config.modrinth_download"),
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_MODRINTH)));
        this.addRenderableWidget(new Button(posX + 100, posY, 100, 20,
                new TranslatableComponent("gui.smsn.missing_cloth_config.curseforge_download"),
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_CURSEFORGE)));
        posY += 25;
        this.addRenderableWidget(new Button(posX, posY, 200, 20,
                CommonComponents.GUI_BACK,
                (b) -> Minecraft.getInstance().setScreen(this.parent)));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        MultiLineLabel.create(this.font, new TranslatableComponent("gui.smsn.missing_cloth_config.title"), 300)
                .renderCentered(poseStack, this.width / 2, this.height / 2 - 45);
        MultiLineLabel.create(this.font, messageComponent, 300)
                .renderCentered(poseStack, this.width / 2, this.height / 2 - 30);
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
}
