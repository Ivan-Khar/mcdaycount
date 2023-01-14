package com.aqupd.mcdaycount;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.aqupd.mcdaycount.Main.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventBusListener {

  @SubscribeEvent
  public static void registerOverlay(RegisterGuiOverlaysEvent event) {
    event.registerAboveAll("daytimecounter", DayTimeRenderGUI::render);
  }

  @SubscribeEvent
  public static void registerKeybinds(RegisterKeyMappingsEvent event) {
    KeyMappings.INSTANCE.registerKeymappings(event);
  }
}
