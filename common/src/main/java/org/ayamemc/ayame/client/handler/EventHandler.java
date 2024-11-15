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

package org.ayamemc.ayame.client.handler;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.ayamemc.ayame.client.screen.AbstractAyameScreen;
import org.ayamemc.ayame.client.screen.constant.TabElement;

import java.util.ArrayList;
import java.util.function.Supplier;

public class EventHandler {
    final private static Minecraft minecraft = Minecraft.getInstance();

    public static void renderCustomHandEventHandler(
            InteractionHand hand,
            PoseStack poseStack,
            MultiBufferSource multiBufferSource,
            int packedLight,
            float partialTick,
            float interpolatedPitch,
            float swingProgress,
            float equipProgress,
            ItemStack stack
    ) {

    }

    public static void openSelectMenuKeyPressed() {
        ArrayList<TabElement> elements=new ArrayList<>();
        for(int i=0;i<5;i++){
            final int num=i;
            elements.add(
                    new TabElement() {

                        @SuppressWarnings("unchecked")
                        @Override
                        public <T extends GuiEventListener & Renderable & NarratableEntry> T createWidget(AbstractAyameScreen screen, int x, int y, int width, int height) {
                            return (T) new Button(x,y,width,height,Component.literal(String.valueOf(num)),(b)->{}, Supplier::get){};
                        }
                    }
            );
        }
        minecraft.setScreen(new AbstractAyameScreen(Component.translatable("测试屏幕"),elements));
    }
}
