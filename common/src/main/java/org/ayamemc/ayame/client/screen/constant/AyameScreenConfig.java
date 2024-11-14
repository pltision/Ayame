package org.ayamemc.ayame.client.screen.constant;

public class AyameScreenConfig implements Cloneable {
    public int tabBarWidth=32;
    public int titleBarHeight=20;

    public int ruleWidth=2;

    public int modelBlockWidth=50;
    public int modelBlockHeight=90;
    public int modelBlockPadding =6;

    public int viewPanelExpectWidth=100;

    @Override
    public AyameScreenConfig clone() {
        try {
            return (AyameScreenConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
