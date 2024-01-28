package com.provismet.datagen.AdditionalArmoury;

import java.util.Map;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.registries.AAStatusEffects;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.enchantment.Enchantment;
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
        translationBuilder.add(AAItems.OVERNETHER_INGOT, "Overnether Ingot");
        translationBuilder.add(AAItems.ENDERNETHER_INGOT, "Endernether Ingot");

        translationBuilder.add("upgrade.additional-armoury.overnether_upgrade", "Overnether Upgrade");
        translationBuilder.add("item.additional-armoury.overnether_upgrade.ingredients", "Overnether Ingot");
        translationBuilder.add("item.additional-armoury.overnether_upgrade.addition", "Add Overnether Ingot");

        translationBuilder.add("upgrade.additional-armoury.endernether_upgrade", "Endernether Upgrade");
        translationBuilder.add("item.additional-armoury.endernether_upgrade.ingredients", "Endernether Ingot");
        translationBuilder.add("item.additional-armoury.endernether_upgrade.addition", "Add Endernether Ingot");

        translationBuilder.add(AAItems.OVERNETHER_SWORD, "Overnether Sword");
        translationBuilder.add(AAItems.OVERNETHER_AXE, "Overnether Axe");
        translationBuilder.add(AAItems.OVERNETHER_PICKAXE, "Overnether Pickaxe");
        translationBuilder.add(AAItems.OVERNETHER_SHOVEL, "Overnether Shovel");
        translationBuilder.add(AAItems.OVERNETHER_HOE, "Overnether Hoe");

        translationBuilder.add(AAItems.ENDERNETHER_SWORD, "Endernether Sword");
        translationBuilder.add(AAItems.ENDERNETHER_AXE, "Endernether Axe");
        translationBuilder.add(AAItems.ENDERNETHER_PICKAXE, "Endernether Pickaxe");
        translationBuilder.add(AAItems.ENDERNETHER_SHOVEL, "Endernether Shovel");
        translationBuilder.add(AAItems.ENDERNETHER_HOE, "Endernether Hoe");

        translationBuilder.add(AAItems.OVERNETHER_HELMET, "Overnether Helmet");
        translationBuilder.add(AAItems.OVERNETHER_CHESTPLATE, "Overnether Chestplate");
        translationBuilder.add(AAItems.OVERNETHER_LEGGINGS, "Overnether Leggings");
        translationBuilder.add(AAItems.OVERNETHER_BOOTS, "Overnether Boots");

        translationBuilder.add(AAItems.ENDERNETHER_HELMET, "Endernether Helmet");
        translationBuilder.add(AAItems.ENDERNETHER_CHESTPLATE, "Endernether Chestplate");
        translationBuilder.add(AAItems.ENDERNETHER_LEGGINGS, "Endernether Leggings");
        translationBuilder.add(AAItems.ENDERNETHER_BOOTS, "Endernether Boots");

        LanguageGenerator.addDagger(translationBuilder, AAItems.WOODEN_DAGGER, "Wooden Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.STONE_DAGGER, "Stone Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.GOLDEN_DAGGER, "Golden Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.IRON_DAGGER, "Iron Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.DIAMOND_DAGGER, "Diamond Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.NETHERITE_DAGGER, "Netherite Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.OVERNETHER_DAGGER, "Overnether Dagger");
        LanguageGenerator.addDagger(translationBuilder, AAItems.ENDERNETHER_DAGGER, "Endernether Dagger");

        translationBuilder.add(AAItems.WOODEN_MACE, "Wooden Mace");
        translationBuilder.add(AAItems.STONE_MACE, "Stone Mace");
        translationBuilder.add(AAItems.IRON_MACE, "Iron Mace");
        translationBuilder.add(AAItems.DIAMOND_MACE, "Diamond Mace");
        translationBuilder.add(AAItems.NETHERITE_MACE, "Netherite Mace");
        translationBuilder.add(AAItems.OVERNETHER_MACE, "Overnether Mace");
        translationBuilder.add(AAItems.ENDERNETHER_MACE, "Endernether Mace");

        translationBuilder.add(AAItems.STAFF, "Unenchanted Staff");
        translationBuilder.add(AAItems.STAFF.getTranslationKey() + ".enchanted", "Enchanted Staff");
        translationBuilder.add(AAItems.BOOMERANG, "Boomerang");

        translationBuilder.add(AAItems.FIREBALL, "Fireball");
        translationBuilder.add(AAItems.FROSTBALL, "Frostball");
        translationBuilder.add(AAItems.GHOSTLY_ORB, "Ghostly Orb");
        translationBuilder.add(AAItems.WIND_TORNADO, "Wind Tornado");
        translationBuilder.add(AAItems.MAGIC_MISSILE, "Magical Missile");

        translationBuilder.add(AAEntityTypes.FIREBALL, "Fireball Spell");
        translationBuilder.add(AAEntityTypes.FROSTBALL, "Frostball Spell");
        translationBuilder.add(AAEntityTypes.GHOSTLY_ORB, "Ghostly Orb Spell");
        translationBuilder.add(AAEntityTypes.WIND_TORNADO, "Wind Tornado Spell");
        translationBuilder.add(AAEntityTypes.MAGIC_MISSILE, "Missile Spell");
        translationBuilder.add(AAEntityTypes.BOOMERANG, "Boomerang");

        translationBuilder.add(AAStatusEffects.SHATTERED, "Shattered");

        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.BOOST, "Boosting", "Launches the user forwards.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.ERUPTION, "Eruption", "Knockbacks all nearby enemies.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.JUMP, "Soaring", "Launch into the air and gain slow-fall.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.FIREBALL, "Fireball", "Shoot a fireball that burns on hit.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.FROSTBALL, "Frostball", "Shoot a frostball that freezes on hit.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.GHOSTLY_ORB, "Ghostly Orb", "Shoot a spectral ball that passes through blocks.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.GALE, "Gale", "Shoot a barrage of tornados that launch targets.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.MAGIC_MISSILE, "Missile", "Shoot a magical missile that follows enemies.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.EXPLOSION, "Bakuretsu Mahou", "Casts the most powerful of artillery magic.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.ADHESIVE, "Adhesive", "Potion-tipped daggers have unlimited uses.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.SPLATTER, "Splatter", "Potion-tipped daggers affect multiple targets at once.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.SHREDDING, "Shredding", "Increases the duration of the shattered effect from a mace.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.RICOCHET, "Ricochet", "Increases the number of boomerang bounces.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.MULTITHROW, "MultiThrow", "Throws 3 boomerangs at once.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.FAR_THROW, "Discus", "Increases boomerang maximum distance.");
        LanguageGenerator.addEnchantment(translationBuilder, AAEnchantments.STRONG_THROW, "Strong Arm", "Increases boomerang damage.");

        LanguageGenerator.addAttackDeathMessage(translationBuilder, "fireball_spell", "was blasted by");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "frostball_spell", "was frozen by");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "eruption_spell", "was launched by");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "ghostly_orb_spell", "was haunted by");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "wind_tornado_spell", "was caught in a tornado from");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "missile_spell", "could not outrun the magic of");
        LanguageGenerator.addAttackDeathMessage(translationBuilder, "boomerang", "was struck by a boomerang from");

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

    private static void addEnchantment (TranslationBuilder translationBuilder, Enchantment enchantment, String name, String description) {
        translationBuilder.add(enchantment, name);
        translationBuilder.add(enchantment.getTranslationKey() + ".desc", description);
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
