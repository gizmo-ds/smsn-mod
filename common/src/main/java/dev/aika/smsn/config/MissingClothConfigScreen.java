package dev.aika.smsn.config;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
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
        this.addRenderableWidget(new Button(posX, posY - 15, 200, 20,
                Component.translatable("gui.smsn.missing_cloth_config.modrinth_download"),
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_MODRINTH)));
        this.addRenderableWidget(new Button(posX, posY - 15 + 30, 200, 20,
                Component.translatable("gui.smsn.missing_cloth_config.curseforge_download"),
                b -> Util.getPlatform().openUri(CLOTH_CONFIG_MODRINTH)));
        this.addRenderableWidget(new Button(posX, posY + 50, 200, 20,
                CommonComponents.GUI_BACK,
                b -> Minecraft.getInstance().setScreen(this.parent)));
    }

    @Override
    public void render(PoseStack poseStack, int i, int j, float f) {
        renderDirtBackground(0);
        drawCenteredString(poseStack, font, title, width / 2, 80, -1);
        this.message.renderCentered(poseStack, this.width / 2, 100);
        super.render(poseStack, i, j, f);
    }
}
