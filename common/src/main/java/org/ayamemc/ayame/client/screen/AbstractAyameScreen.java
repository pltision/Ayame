package org.ayamemc.ayame.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.ayamemc.ayame.client.screen.constant.AyameScreenConfig;
import org.ayamemc.ayame.client.screen.constant.ScreenBlitConstants;
import org.ayamemc.ayame.client.screen.constant.TabElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.ayamemc.ayame.util.ResourceLocationHelper.withAyameNamespace;

public class AbstractAyameScreen extends Screen {

    List<TabElement> tabElements;

    public AbstractAyameScreen(Component title, List<TabElement> tabElements) {
        super(title);
        this.tabElements=tabElements;
    }

    public ScreenBlitConstants constants=new ScreenBlitConstants();
    public AyameScreenConfig screenConfig=new AyameScreenConfig();

    public static String WHITE="white";
    public static String AQUA="aqua";
    public static String PINK="pink";

    public static ResourceLocation BACKGROUND_COLOR= withAyameNamespace("textures/gui/background.png");

    public static ResourceLocation TITLE_BAR = withAyameNamespace("textures/gui/screen/title_bar.png");

    protected List<Renderable> widgets;

    @Override
    protected void init() {
        super.init();
        widgets=new ArrayList<>();
        constants.build(width,height,screenConfig);
        initTabBar();
    }


    public void initExpandTabsButton(){
        widgets.add(addWidget(new Button(constants.tabBar.x,constants.tabBar.y,screenConfig.tabBarWidth,screenConfig.expandTabsButtonHeight,Component.literal(">"),(b)->{}, Supplier::get){}));
    }


    public void initTabBar(){
        int x=constants.tabBar.x;
        int y=constants.tabBar.y+ screenConfig.expandTabsButtonHeight;

        initExpandTabsButton();
        for(TabElement element:tabElements){
            widgets.add(addWidget(element.createWidget(this,x,y,screenConfig.tabBarWidth,element.tabHeight())));
            y+=element.tabHeight();
        }
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
//        super.render(graphics, mouseX, mouseY, partialTick);
        super.renderBackground(graphics, mouseX, mouseY, partialTick);

        renderTitleBar(graphics,mouseX,mouseY,partialTick);

        renderColor(graphics,constants.tabBar.x,constants.tabBar.y,constants.tabBar.width,constants.tabBar.height,WHITE);
        renderColor(graphics,constants.tabBarRule.x,constants.tabBarRule.y,constants.tabBarRule.width,constants.tabBarRule.height,PINK);
//        renderColor(graphics,constants.titleBarRule.x,constants.titleBarRule.y,constants.titleBarRule.width,constants.titleBarRule.height,AQUA);
//        renderColor(graphics,constants.contentPanel.x,constants.contentPanel.y,constants.contentPanel.width,constants.contentPanel.height,WHITE);
        renderColor(graphics,constants.viewPanelRule.x,constants.viewPanelRule.y,constants.viewPanelRule.width,constants.viewPanelRule.height,PINK);
//        renderColor(graphics,constants.viewPanel.x,constants.viewPanel.y,constants.viewPanel.width,constants.viewPanel.height,WHITE);
        renderWidgets(graphics,mouseX,mouseY,partialTick);
    }

    public void renderWidgets(GuiGraphics graphics, int mouseX, int mouseY, float partialTick){
        graphics.pose().pushPose();
        graphics.pose().translate(0,0,2000);

        for (Renderable renderable : this.widgets) {
            renderable.render(graphics, mouseX, mouseY, partialTick);
        }

        graphics.pose().popPose();
    }

    public void renderTitleBar(GuiGraphics graphics, int mouseX, int mouseY, float partialTick){
        graphics.pose().pushPose();
        graphics.pose().translate(constants.titleBar.x,constants.titleBar.y,1000);

        graphics.blit(TITLE_BAR,0,0,0,0,constants.titleBar.width,constants.titleBar.height,720,17);
//        graphics.drawString(font, getTitle(),screenConfig.titleDx+1,screenConfig.titleDy+1,0xff808080,false);
        graphics.drawString(font, getTitle(),screenConfig.titleDx,screenConfig.titleDy,0xff000000,false);

        graphics.pose().popPose();
    }


    public void renderColor(GuiGraphics graphics,int x,int y,int w,int h,String color){
        graphics.blit(ResourceLocation.fromNamespaceAndPath("testpltision","textures/gui/"+color+".png"),x,y,0,0,w,h,100,100);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void renderMenuBackground(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        /*System.out.println(Screen.MENU_BACKGROUND);
        try {
            Field field= Screen.class.getField("INWORLD_MENU_BACKGROUND");
            field.setAccessible(true);
            System.out.println(field.get(null) );
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        renderMenuBackgroundTexture(guiGraphics, BACKGROUND_COLOR, x, y, 0.0F, 0.0F, width, height);

    }
}
