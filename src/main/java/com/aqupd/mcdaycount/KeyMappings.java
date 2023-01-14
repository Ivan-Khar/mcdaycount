package com.aqupd.mcdaycount;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

public class KeyMappings {
  private KeyMappings() {}
  public static final KeyMappings INSTANCE = new KeyMappings();

  KeyMapping openConfig = new KeyMapping("keybinding.mcdaycount.config", 1, "keybinding.mcdaycount.category");

  public void registerKeymappings(RegisterKeyMappingsEvent event) {
    event.register(openConfig);
  }
}
