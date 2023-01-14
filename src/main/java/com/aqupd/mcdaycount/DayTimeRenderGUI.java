package com.aqupd.mcdaycount;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class DayTimeRenderGUI {

  public static void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
    Minecraft minecraft = Minecraft.getInstance();
    Window window = minecraft.getWindow();
    Config config = Config.INSTANCE;
    String text = String.format(config.hudtext, Vars.INSTANCE.dayCount);
    int xPos = window.getGuiScaledWidth()/100*config.xPos;
    int yPos = window.getGuiScaledHeight()/100*config.yPos;

    gui.setupOverlayRenderState(true, false, null);

    Font font = minecraft.font;
    int argb = ((config.bgColor.getAlpha()&0x0ff)<<24)|((config.bgColor.getRed()&0x0ff)<<16)|((config.bgColor.getGreen()&0x0ff)<<8)|(config.bgColor.getBlue()&0x0ff);
    GuiComponent.fill(poseStack, xPos, yPos, xPos + font.width(text) + 1, yPos + font.lineHeight + 1, argb);

    int rgb = ((config.textColor.getRed()&0x0ff)<<16)|((config.textColor.getGreen()&0x0ff)<<8)|(config.textColor.getBlue()&0x0ff);
    font.draw(poseStack, text, xPos + 1, yPos + 1, rgb);
  }

}
