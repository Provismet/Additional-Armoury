package com.provismet.AdditionalArmoury.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.CombatPlusCore.utility.CPCConfig;
import com.provismet.lilylib.util.json.JsonBuilder;
import com.provismet.lilylib.util.json.JsonReader;

public class AASettings {
    private static final String FILE = "additional-armoury.json";

    private static boolean overrideDatapacks = true;

    public static void write () {
        String jsonString = new JsonBuilder()
            .append(CPCConfig.KEY_OVERRIDE_DATAPACK_LOOT_TABLES, overrideDatapacks)
            .toString();
        
        try (FileWriter writer = new FileWriter(new File(CPCConfig.FOLDER, FILE))) {
            writer.write(jsonString);
        }
        catch (IOException e) {
            AdditionalArmouryMain.LOGGER.error("Error whilst saving config: ", e);
        }
    }

    public static void read () {
        try {
            JsonReader reader = JsonReader.file(new File(CPCConfig.FOLDER, FILE));
            if (reader != null) {
                reader.getBoolean(CPCConfig.KEY_OVERRIDE_DATAPACK_LOOT_TABLES).ifPresent(val -> AASettings.overrideDatapacks = val);
            }
        }
        catch (FileNotFoundException e) {
            AdditionalArmouryMain.LOGGER.info("No config found for Additional Armoury, creating one now.");
            try {
                (new File(CPCConfig.FOLDER)).mkdirs();
            }
            catch (Exception ignored) {

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
