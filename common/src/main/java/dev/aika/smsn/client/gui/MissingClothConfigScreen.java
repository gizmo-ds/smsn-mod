package dev.aika.smsn.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class MissingClothConfigScreen extends Screen {
    private final static String CLOTH_CONFIG_MODRINTH = "https://modrinth.com/mod/9s6osm5g";
    private final static String CLOTH_CONFIG_CURSEFORGE = "https://www.curseforge.com/minecraft/mc-mods/cloth-config";

    private final Component messageComponent = Component.translatable("gui.smsn.missing_cloth_config.message");
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
        posY += font.wordWrapHeight(messageComponent, 300);
        addRenderableWidget(new Button(posX, posY, 100, 20, curseforgeButton,
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_CURSEFORGE)));
        addRenderableWidget(new Button(posX + 100, posY, 100, 20,modrinthButton,
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_MODRINTH)));
        addRenderableWidget(new Button(posX, posY + 25, 200, 20,CommonComponents.GUI_BACK, this::onBack));
    }

    private void onBack(Button ignoredButton) {
        Minecraft.getInstance().setScreen(parent);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        MultiLineLabel.create(font, title, 300).renderCentered(poseStack, width / 2, height / 2 - 45);
        MultiLineLabel.create(font, messageComponent, 300).renderCentered(poseStack, width / 2, height / 2 - 30);
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
}
