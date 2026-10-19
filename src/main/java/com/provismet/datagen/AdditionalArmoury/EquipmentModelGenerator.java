package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.items.AAArmourMaterials;
import com.provismet.lilylib.datagen.provider.LilyEquipmentAssetProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;
import java.util.function.BiConsumer;

public class EquipmentModelGenerator extends LilyEquipmentAssetProvider {
    public EquipmentModelGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    protected void generate (BiConsumer<Identifier, EquipmentClientInfo> consumer) {
        this.generateArmourMaterial(AAArmourMaterials.OVERNETHER.baseMaterial().assetId().identifier(), consumer);
        this.generateArmourMaterial(AAArmourMaterials.ENDERNETHER.baseMaterial().assetId().identifier(), consumer);
    }

    protected final void generateArmourMaterial (Identifier modelId, BiConsumer<Identifier,EquipmentClientInfo> consumer) {
        consumer.accept(modelId, this.buildHumanoid(modelId));
    }
}
