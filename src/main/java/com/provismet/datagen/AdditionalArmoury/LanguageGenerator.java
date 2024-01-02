package com.provismet.datagen.AdditionalArmoury;

import java.util.Map;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;

public class LanguageGenerator extends FabricLanguageProvider {
    protected LanguageGenerator (FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations (TranslationBuilder translationBuilder) {
        LanguageGenerator.addDagger(translationBuilder, AAItems.WOODEN_DAGGER, "Wooden Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.STONE_DAGGER, "Stone Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.GOLDEN_DAGGER, "Golden Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.IRON_DAGGER, "Iron Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.DIAMOND_DAGGER, "Diamond Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.NETHERITE_DAGGER, "Netherite Dagger");

        translationBuilder.add(AAItems.STAFF, "Unenchanted Staff");
        translationBuilder.add(AAItems.STAFF.getTranslationKey() + ".enchanted", "Enchanted Staff");

        translationBuilder.add(AAItems.FIREBALL, "Fireball");
        translationBuilder.add(AAItems.FROSTBALL, "Frostball");

        translationBuilder.add(AAEntityTypes.FIREBALL, "Fireball Spell");
        translationBuilder.add(AAEntityTypes.FROSTBALL, "Frostball Spell");

        translationBuilder.add(AAEnchantments.BOOST, "Boosting");
        translationBuilder.add(AAEnchantments.ERUPTION, "Eruption");
        translationBuilder.add(AAEnchantments.JUMP, "Soaring");
        translationBuilder.add(AAEnchantments.FIREBALL, "Fireball");
        translationBuilder.add(AAEnchantments.FROSTBALL, "Frostball");

        LanguageGenerator.addAttackDeathMessage(translationBuilder, "fireball_spell", "was blasted by");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "frostball_spell", "was frozen by");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "eruption_spell", "was launched by");

        translationBuilder.add("tooltip.additional-armoury.dagger_uses", "Remaining Splashes: %1$s");
    }
    
    public static void addDagger (TranslationBuilder translationBuilder, DaggerItem dagger, String basename) {
        translationBuilder.add(dagger, basename);

        for (Potion potionEntry : Registries.POTION.getEntrySet().stream().map(Map.Entry::getValue).toList()) {
            String effectKey = dagger.getTranslationKey(PotionUtil.setPotion(dagger.getDefaultStack(), potionEntry));
            String[] keySplit = effectKey.split("[.]");
            String potionBasename = LanguageGenerator.titleCase(keySplit[keySplit.length - 1].replace('_', ' '));

            try {
                if (potionEntry != Potions.AWKWARD && potionEntry != Potions.MUNDANE && potionEntry != Potions.THICK && potionEntry.getEffects().size() > 0) {
                    translationBuilder.add(effectKey, potionBasename + "-Tipped " + basename);
                }
                else if (potionEntry == Potions.WATER) {
                    translationBuilder.add(effectKey, "Wet " + basename);
                }
                else {
                    translationBuilder.add(effectKey, basename);
                }
            }
            catch (RuntimeException e) {

            }
        }
    }

    private static String titleCase (String string) {
        String[] words = string.split(" ");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            for (int j = 0; j < word.length(); ++j) {
                char character = word.charAt(j);

                if (j == 0) builder.append(Character.toUpperCase(character));
                else builder.append(Character.toLowerCase(character));
            }

            if (i < words.length - 1) builder.append(" ");
        }

        return builder.toString();
    }

    private static void addAttackDeathMessage (TranslationBuilder translationBuilder, String suffix, String message) {
        translationBuilder.add("death.attack." + suffix, "%1$s " + message + " %2$s");
        translationBuilder.add("death.attack." + suffix + ".item", "%1$s " + message + " %2$s using %3$s");
    }
}
