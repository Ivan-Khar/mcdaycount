package com.aqupd.mcdaycount;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.network.chat.Component;

public class ConfigGUI {
  public static ConfigBuilder getConfigGUI() {
    Config cfg = Config.INSTANCE;
    ConfigBuilder builder = ConfigBuilder.create()
        .setTitle(Component.translatable("title.mcdaycount.config"))
        .setSavingRunnable(cfg::save)
        .transparentBackground();
    ConfigEntryBuilder entryBuilder = builder.entryBuilder();
    ConfigCategory config = builder.getOrCreateCategory(Component.translatable("category.mcdaycount.category"));

    SubCategoryBuilder positionCategory = entryBuilder.startSubCategory(Component.translatable("category.mcdaycount.position")).setExpanded(true);
    positionCategory.add(entryBuilder.startIntSlider(Component.translatable("entry.position.x.name"), cfg.xPos, 0, 100)
        .setDefaultValue(1)
        .setSaveConsumer(newValue -> cfg.xPos = newValue)
        .build());
    positionCategory.add(entryBuilder.startIntSlider(Component.translatable("entry.position.y.name"), cfg.yPos, 0, 100)
        .setDefaultValue(4)
        .setSaveConsumer(newValue -> cfg.yPos = newValue)
        .build());
    config.addEntry(positionCategory.build());

    SubCategoryBuilder stringCategory = entryBuilder.startSubCategory(Component.translatable("category.mcdaycount.string")).setExpanded(true);
    stringCategory.add(entryBuilder.startTextField(Component.translatable("entry.stringtext.name"), cfg.hudtext)
        .setDefaultValue("Day: %d")
        .setSaveConsumer(newValue -> cfg.hudtext = newValue)
        .build());
    stringCategory.add(entryBuilder.startColorField(Component.translatable("entry.stringcolor.name"), cfg.textColor)
        .setDefaultValue(0xFFFFFF)
        .setSaveConsumer2(newValue -> cfg.textColor = newValue)
        .build());
    config.addEntry(stringCategory.build());

    int argb = ((cfg.bgColor.getAlpha()&0x0ff)<<24)|((cfg.bgColor.getRed()&0x0ff)<<16)|((cfg.bgColor.getGreen()&0x0ff)<<8)|(cfg.bgColor.getBlue()&0x0ff);
    SubCategoryBuilder bgCategory = entryBuilder.startSubCategory(Component.translatable("category.mcdaycount.background")).setExpanded(true);
    bgCategory.add(entryBuilder.startAlphaColorField(Component.translatable("entry.backgroundcolor.name"), argb)
        .setDefaultValue(0x7f414141)
        .setAlphaMode(true)
        .setSaveConsumer2(newValue -> cfg.bgColor = newValue)
        .build());
    config.addEntry(bgCategory.build());

    return builder;
  }
}
