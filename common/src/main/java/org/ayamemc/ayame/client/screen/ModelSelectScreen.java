package org.ayamemc.ayame.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.ayamemc.ayame.client.screen.constant.AyameScreenConfig;
import org.ayamemc.ayame.client.screen.constant.ScreenBlitConstants;

public class ModelSelectScreen extends Screen {

    public ModelSelectScreen(Component title) {
        super(title);
    }
    public ScreenBlitConstants constants=new ScreenBlitConstants();

    public static String WHITE="white";
    public static String AQUA="aqua";
    public static String PINK="pink";

    @Override
    protected void init() {
        super.init();
        constants.build(width,height,new AyameScreenConfig());

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        renderColor(guiGraphics,constants.tabBar.x,constants.tabBar.y,constants.tabBar.width,constants.tabBar.height,WHITE);
        renderColor(guiGraphics,constants.tabBarRule.x,constants.tabBarRule.y,constants.tabBarRule.width,constants.tabBarRule.height,PINK);
        renderColor(guiGraphics,constants.titleBar.x,constants.titleBar.y,constants.titleBar.width,constants.titleBar.height,WHITE);
        renderColor(guiGraphics,constants.titleBarRule.x,constants.titleBarRule.y,constants.titleBarRule.width,constants.titleBarRule.height,AQUA);
//        renderColor(guiGraphics,constants.contentPanel.x,constants.contentPanel.y,constants.contentPanel.width,constants.contentPanel.height,WHITE);
        renderColor(guiGraphics,constants.viewPanelRule.x,constants.viewPanelRule.y,constants.viewPanelRule.width,constants.viewPanelRule.height,PINK);
//        renderColor(guiGraphics,constants.viewPanel.x,constants.viewPanel.y,constants.viewPanel.width,constants.viewPanel.height,WHITE);
    }

    public void renderColor(GuiGraphics graphics,int x,int y,int w,int h,String color){
        graphics.blit(ResourceLocation.fromNamespaceAndPath("testpltision","textures/gui/"+color+".png"),x,y,0,0,w,h,1000,1000);
    }


}
