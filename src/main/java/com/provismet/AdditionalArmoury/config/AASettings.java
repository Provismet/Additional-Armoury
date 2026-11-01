package com.provismet.AdditionalArmoury.config;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.CombatPlusCore.utility.CombatPlusConfig;
import com.provismet.lilylib.util.json.JsonBuilder;
import com.provismet.lilylib.util.json.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class AASettings {
    private static final Path FILE = CombatPlusConfig.getConfigDirectory().resolve("additional-armoury.json");

    private static boolean overrideDatapacks = true;

    public static void write () {
        String jsonString = new JsonBuilder()
            .append(CombatPlusConfig.KEY_OVERRIDE_DATAPACK_LOOT_TABLES, overrideDatapacks)
            .toString();
        
        try (FileWriter writer = new FileWriter(FILE.toFile())) {
            writer.write(jsonString);
        }
        catch (IOException e) {
            AdditionalArmouryMain.LOGGER.error("Error whilst saving config: ", e);
        }
    }

    public static void read () {
        try {
            JsonReader reader = JsonReader.file(FILE.toFile());
            if (reader != null) {
                reader.getBoolean(CombatPlusConfig.KEY_OVERRIDE_DATAPACK_LOOT_TABLES).ifPresent(val -> AASettings.overrideDatapacks = val);
            }
        }
        catch (FileNotFoundException e) {
            AdditionalArmouryMain.LOGGER.info("No config found for Additional Armoury, creating one now.");
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
