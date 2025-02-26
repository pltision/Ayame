/*
 *     Custom player model mod. Powered by GeckoLib.
 *     Copyright (C) 2024  CrystalNeko, HappyRespawnanchor, pertaz(Icon Designer)
 *
 *     This file is part of Ayame.
 *
 *     Ayame is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Ayame is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with Ayame.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.ayamemc.ayame.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.ayamemc.ayame.client.gui.widget.BlurWidget;
import org.ayamemc.ayame.util.ConfigUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.ayamemc.ayame.util.ResourceLocationHelper.withAyameNamespace;

@Environment(EnvType.CLIENT)
public abstract class AyameScreen extends Screen {
    public static final ResourceLocation MENU_BACKGROUND_TEXTURE = withAyameNamespace("textures/gui/background.png");
    public static final ResourceLocation MENU_BACKGROUND_OUTLINE_TEXTURE = withAyameNamespace("textures/gui/background_outline.png");
    public static final ResourceLocation MENU_TOP_LAYER_TEXTURE = withAyameNamespace("textures/gui/top_layer.png");
    protected static final int BACKGROUND_TEXTURE_WIDTH = 410;
    protected static final int BACKGROUND_TEXTURE_HEIGHT = 220;
    protected int leftPos;
    protected int topPos;
    protected static final int MINI_BUTTON_SIZE = 16;
    private static final int BUTTON_SIZE = 32;
    protected final boolean skipWarningOnce;
    protected Screen lastScreen;

    /**
     * 构造方法，允许设置是否单次跳过警告界面。
     *
     * @param lastScreen      上一个屏幕
     * @param skipWarningOnce 是否跳过一次警告界面
     */
    public AyameScreen(@Nullable Screen lastScreen, boolean skipWarningOnce) {
        super(Component.empty());
        this.lastScreen = lastScreen;
        this.skipWarningOnce = skipWarningOnce;
    }

    public AyameScreen(@Nullable Screen lastScreen) {
        super(Component.empty());
        this.lastScreen = lastScreen;
        this.skipWarningOnce = false;
    }


    @Override
    protected void init() {
        leftPos = (this.width - BACKGROUND_TEXTURE_WIDTH) / 2;
        topPos = (this.height - BACKGROUND_TEXTURE_HEIGHT) / 2;
        // 检查是否需要显示警告界面
        if (!ConfigUtil.SKIP_AYAME_WARNING && !skipWarningOnce) {
            this.minecraft.setScreen(new StatementScreen(null, lastScreen));
            return;
        }

        // 显示模糊背景
        BlurWidget blurredBackgroundWidget = new BlurWidget(getCenteredX(BACKGROUND_TEXTURE_WIDTH), getCenteredY(BACKGROUND_TEXTURE_HEIGHT), BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        this.addRenderableOnly(blurredBackgroundWidget);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (minecraft.level == null) {
            super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
            renderBackgroundTexture(guiGraphics, mouseX, mouseY, partialTick);
        } else {
            renderBackgroundTexture(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    protected void renderBackgroundTexture(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.enableBlend();
        guiGraphics.blit(MENU_BACKGROUND_TEXTURE, getCenteredX(BACKGROUND_TEXTURE_WIDTH), getCenteredY(BACKGROUND_TEXTURE_HEIGHT), 0, 0, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        RenderSystem.disableBlend();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        RenderSystem.enableBlend();
        guiGraphics.blit(MENU_BACKGROUND_OUTLINE_TEXTURE, getCenteredX(BACKGROUND_TEXTURE_WIDTH), getCenteredY(BACKGROUND_TEXTURE_HEIGHT), 0, 0, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        guiGraphics.blit(renderTopLayerResourceLocation(), getCenteredX(BACKGROUND_TEXTURE_WIDTH), getCenteredY(BACKGROUND_TEXTURE_HEIGHT), 0, 0, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        RenderSystem.disableBlend();

        WidgetSprites settingSprites = new WidgetSprites(
                withAyameNamespace("settings"),
                withAyameNamespace("settings_disabled"),
                withAyameNamespace("settings_enabled_focused")
        );

        ImageButton settingsButton = new ImageButton(
                getAlignedX(BACKGROUND_TEXTURE_WIDTH, BUTTON_SIZE, 0, Alignment.LEFT),
                getAlignedY(BACKGROUND_TEXTURE_HEIGHT, BUTTON_SIZE, 0, Alignment.BOTTOM),
                BUTTON_SIZE,
                BUTTON_SIZE,
                settingSprites,
                button -> {
                    minecraft.setScreen(new SettingsScreen(this,true));
                },
                Component.translatable("ayame.screen.warningscreen.settingsscreen.title")
        );


        addRenderableWidget(settingsButton);

        Component titleText = Component.translatable(setTranslatableTitle());
        int centerX = getCenteredStringX(titleText);
        guiGraphics.drawString(this.font, titleText, centerX, font.lineHeight, 0xFFFFFFFF, true);
    }

    protected abstract @NotNull ResourceLocation renderTopLayerResourceLocation();

    protected abstract @NotNull String setTranslatableTitle();

    protected int getCenteredX(int elementWidth) {
        return (this.width - elementWidth) / 2;
    }

    protected int getCenteredY(int elementHeight) {
        return (this.height - elementHeight) / 2;
    }

    protected int getAlignedX(int containerWidth, int elementWidth, int margin, Alignment alignment) {
        int baseX = getCenteredX(containerWidth);
        return switch (alignment) {
            case LEFT -> baseX + margin;
            case RIGHT -> baseX + containerWidth - elementWidth - margin;
            default -> baseX + (containerWidth - elementWidth) / 2;
        };
    }

    protected int getCenteredStringX(Component text) {
        int textWidth = this.font.width(text);
        return (this.width - textWidth) / 2;
    }


    protected int getAlignedY(int containerHeight, int elementHeight, int margin, Alignment alignment) {
        int baseY = getCenteredY(containerHeight);
        return switch (alignment) {
            case TOP -> baseY + margin;
            case BOTTOM -> baseY + containerHeight - elementHeight - margin;
            default -> baseY + (containerHeight - elementHeight) / 2;
        };
    }

    @Override
    public void onClose() {
        minecraft.setScreen(lastScreen);
    }

    protected enum Alignment {
        CENTER,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
    }
}