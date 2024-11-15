package org.ayamemc.ayame.client.screen.constant;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import org.ayamemc.ayame.client.screen.AbstractAyameScreen;

public interface TabElement  {
    default int tabHeight(){
        return 24;
    }

    <T extends GuiEventListener & Renderable & NarratableEntry> T createWidget(AbstractAyameScreen screen,int x,int y,int width,int height);
}
