package com.provismet.additionalarmoury.references;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public interface AABlockItemIds {
    BlockItemId OVERNETHER_BLOCK = create("overnether_block");
    BlockItemId ENDERNETHER_BLOCK = create("endernether_block");

    private static BlockItemId create (String path) {
        Identifier id = AdditionalArmouryMain.identifier(path);
        return BlockItemId.create(id, id);
    }
}
