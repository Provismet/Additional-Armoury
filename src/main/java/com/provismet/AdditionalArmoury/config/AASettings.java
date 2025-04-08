package com.provismet.AdditionalArmoury.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.lilylib.util.JsonBuilder;

public class AASettings {
    private static boolean overrideDatapacks = true;

    public static void write () {
        JsonBuilder builder = new JsonBuilder();
        String jsonString = builder.start()
            .append("override_datapack_loot_tables", overrideDatapacks).newLine(false)
            .closeObject()
            .toString();
        
        try {
            FileWriter writer = new FileWriter("config/combat-plus/additional-armoury.json");
            writer.write(jsonString);
            writer.close();
        }
        catch (IOException e) {
            AdditionalArmouryMain.LOGGER.error("Error whilst saving config: ", e);
        }
    }

    public static void read () {
        try {
            JsonElement element = JsonParser.parseReader(new FileReader("config/combat-plus/additional-armoury.json"));
            if (element instanceof JsonObject json) {
                if (json.has("override_datapack_loot_tables")) AASettings.overrideDatapacks = json.getAsJsonPrimitive("override_datapack_loot_tables").getAsBoolean();
            }
        }
        catch (FileNotFoundException e) {
            AdditionalArmouryMain.LOGGER.info("No config found for Additional Armoury, creating one now.");
            try {
                (new File("config/combat-plus")).mkdirs();
            }
            catch (Exception e3) {

            }
            AASettings.write();
        }
        catch (Exception e2) {
            AdditionalArmouryMain.LOGGER.error("Error whilst parsing config:", e2);
        }
    }

    public static boolean shouldOverrideDatapacks () {
        return AASettings.overrideDatapacks;
    }
}
