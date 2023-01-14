package com.aqupd.mcdaycount;

import com.google.gson.*;
import me.shedaniel.math.Color;

import javax.json.Json;
import java.io.*;

import static com.aqupd.mcdaycount.Main.*;

public class Config {
  private Config() {}
  public static final Config INSTANCE = new Config();
  private final File confFile = new File("./config/mcdaycount.json");
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public int xPos = 1, yPos = 4;
  public String hudtext = "Day: %d";
  public Color bgColor = Color.ofRGBA(65, 65, 65, 127);
  public Color textColor = Color.ofRGB(255, 255, 255);

  public void load() {
    if (!confFile.exists() || confFile.length() == 0) save();
    try {
      JsonObject jo = gson.fromJson(new FileReader(confFile), JsonObject.class);
      JsonElement je;
      if((je = jo.get("xPos")) != null) xPos = je.getAsInt();
      if((je = jo.get("yPos")) != null) yPos = je.getAsInt();
      if((je = jo.get("bgcolor")) != null) {
        JsonObject data = je.getAsJsonObject();
        bgColor = Color.ofRGBA(data.get("red").getAsInt(), data.get("green").getAsInt(), data.get("blue").getAsInt(), data.get("alpha").getAsInt());
      }
      if((je = jo.get("textcolor")) != null) {
        JsonObject data = je.getAsJsonObject();
        textColor = Color.ofRGB(data.get("red").getAsInt(), data.get("green").getAsInt(), data.get("blue").getAsInt());
      }
      if((je = jo.get("hudtext")) != null) hudtext = je.getAsString();
    } catch (FileNotFoundException ex) {
      LOGGER.trace("Couldn't load configuration file", ex);
    }
    save();
  }
  public void save() {
    try {
      if (!confFile.exists()) { confFile.getParentFile().mkdirs(); confFile.createNewFile(); }

      JsonObject jo = new JsonObject();
      jo.add("xPos", new JsonPrimitive(xPos));
      jo.add("yPos", new JsonPrimitive(yPos));
      jo.add("hudtext", new JsonPrimitive(hudtext));

      JsonObject bgclr = new JsonObject();
      bgclr.add("red", new JsonPrimitive(bgColor.getRed()));
      bgclr.add("green", new JsonPrimitive(bgColor.getGreen()));
      bgclr.add("blue", new JsonPrimitive(bgColor.getBlue()));
      bgclr.add("alpha", new JsonPrimitive(bgColor.getAlpha()));
      jo.add("bgcolor", bgclr);
      //je.getAsInt()
      JsonObject textclr = new JsonObject();
      textclr.add("red", new JsonPrimitive(textColor.getRed()));
      textclr.add("green", new JsonPrimitive(textColor.getGreen()));
      textclr.add("blue", new JsonPrimitive(textColor.getBlue()));
      jo.add("textcolor", textclr);

      PrintWriter printwriter = new PrintWriter(new FileWriter(confFile));
      printwriter.print(gson.toJson(jo));
      printwriter.close();
    } catch (IOException ex) {
      LOGGER.trace("Couldn't save configuration file", ex);
    }
  }
}
