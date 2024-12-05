package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.items.AAArmourMaterials;
import com.provismet.lilylib.datagen.provider.LilyEquipmentAssetProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class EquipmentModelGenerator extends LilyEquipmentAssetProvider {
    public EquipmentModelGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    protected void generate (BiConsumer<Identifier, EquipmentModel> consumer) {
        this.generateArmourMaterial(AAArmourMaterials.OVERNETHER.baseMaterial().assetId().getValue(), consumer);
        this.generateArmourMaterial(AAArmourMaterials.ENDERNETHER.baseMaterial().assetId().getValue(), consumer);
    }

    protected final void generateArmourMaterial (Identifier modelId, BiConsumer<Identifier,EquipmentModel> consumer) {
        consumer.accept(modelId, this.buildHumanoid(modelId));
    }
}
