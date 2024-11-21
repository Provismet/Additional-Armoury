package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.items.AAArmourMaterials;
import com.provismet.lilylib.datagen.provider.LilyEquipmentModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.equipment.EquipmentModel;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class EquipmentModelGenerator extends LilyEquipmentModelProvider {
    public EquipmentModelGenerator (FabricDataOutput output) {
        super(output);
    }

    protected void generate (BiConsumer<Identifier,EquipmentModel> consumer) {
        this.generateArmourMaterial(AAArmourMaterials.OVERNETHER.baseMaterial().modelId(), consumer);
        this.generateArmourMaterial(AAArmourMaterials.ENDERNETHER.baseMaterial().modelId(), consumer);
    }

    protected final void generateArmourMaterial (Identifier modelId, BiConsumer<Identifier,EquipmentModel> consumer) {
        consumer.accept(modelId, this.buildHumanoid(modelId));
    }
}
