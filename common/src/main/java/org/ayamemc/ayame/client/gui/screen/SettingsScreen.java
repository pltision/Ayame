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

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SettingsScreen extends AyameScreen {
    /**
     * 构造方法，允许设置是否单次跳过警告界面。
     *
     * @param lastScreen      上一个屏幕
     * @param skipWarningOnce 是否跳过一次警告界面
     */
    public SettingsScreen(@Nullable Screen lastScreen, boolean skipWarningOnce) {
        super(lastScreen, skipWarningOnce);
    }
    public SettingsScreen(@Nullable Screen lastScreen) {
        super(lastScreen, false);
    }



    @Override
    protected @NotNull ResourceLocation renderTopLayerResourceLocation() {
        return MENU_TOP_LAYER_TEXTURE;
    }

    @Override
    protected @NotNull String setTranslatableTitle() {
        return "ayame.screen.warningscreen.settingsscreen.title";
    }
}
