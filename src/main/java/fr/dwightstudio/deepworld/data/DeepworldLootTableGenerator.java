package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class DeepworldLootTableGenerator extends FabricBlockLootTableProvider {

    public DeepworldLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(DeepworldBlocks.STEEL_BLOCK);
        addDrop(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);
    }
}
