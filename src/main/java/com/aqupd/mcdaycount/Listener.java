package com.aqupd.mcdaycount;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.aqupd.mcdaycount.Main.*;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class Listener {

  @SubscribeEvent
  public static void onClientTick(TickEvent.ClientTickEvent event) {
    Minecraft mc = Minecraft.getInstance();
    if (mc.level != null) {
      Vars.INSTANCE.dayCount = mc.level.getDayTime() / 24000L % 2147483647L;
    }

    if(KeyMappings.INSTANCE.openConfig.isDown()) {
      Screen screen = ConfigGUI.getConfigGUI().setParentScreen(Minecraft.getInstance().screen).build();
      Minecraft.getInstance().setScreen(screen);
    }
  }
}
