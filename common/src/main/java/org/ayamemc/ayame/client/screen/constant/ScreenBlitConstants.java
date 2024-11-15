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

package org.ayamemc.ayame.client.screen.constant;


public class ScreenBlitConstants {
    //导航栏
    public PlacePairs tabBar;
    //导航栏左侧的分割线
    public PlacePairs tabBarRule;

    //标题栏
    public PlacePairs titleBar;
    //标题栏下面的分割线
//    public PlacePairs titleBarRule;

    //内容区
    public PlacePairs contentPanel;
    //无预览区域时内容区宽度
    public int contentPanelWithNoViewPanel;

    //预览区
    public PlacePairs viewPanel;
    //内容区与预览区的分割线
    public PlacePairs viewPanelRule;
    //预览区及分割线所占的宽度
    public int viewPanelWidthSub;


    public void build(int screenWidth,int screenHeight,AyameScreenConfig config){

        int widthSum=0;

        tabBar=new PlacePairs(0,0,config.tabBarWidth,screenHeight);
        widthSum+=tabBar.width;

        tabBarRule=new PlacePairs(widthSum,0,config.ruleWidth,screenHeight);
        widthSum+=tabBarRule.width;

        int heightSum=0;

        titleBar=new PlacePairs(widthSum,0,screenWidth-widthSum,config.titleBarHeight);
        heightSum+= titleBar.height;

//        titleBarRule=new PlacePairs(widthSum,heightSum,screenWidth-widthSum,config.ruleWidth);
//        heightSum+= titleBarRule.height;

        contentPanelWithNoViewPanel=screenWidth-widthSum;

        int preModelWidth=(config.modelBlockWidth+config.modelBlockPadding);
        int modelCount=(screenWidth-widthSum-config.viewPanelExpectWidth-config.modelBlockPadding)/preModelWidth;

        contentPanel=new PlacePairs(widthSum,heightSum,(modelCount>0?modelCount:1)*preModelWidth+config.modelBlockPadding,screenHeight-heightSum);
        widthSum+=contentPanel.width;

        viewPanelRule=new PlacePairs(widthSum,heightSum,config.ruleWidth,screenHeight-heightSum);
        widthSum+=viewPanelRule.width;

        viewPanel=new PlacePairs(widthSum,heightSum,screenWidth-widthSum,screenHeight-heightSum);

        viewPanelWidthSub=viewPanelRule.width+viewPanel.width;

    }



}
