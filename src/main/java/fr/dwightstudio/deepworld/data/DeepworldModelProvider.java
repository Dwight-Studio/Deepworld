package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class DeepworldModelProvider extends FabricModelProvider {

    public DeepworldModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * Generate both blockstates and models block's files
     *
     * @param blockStateModelGenerator
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(DeepworldBlocks.STEEL_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(DeepworldItems.IRON_GEAR, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.IRON_NAIL, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.IRON_PLATE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.IRON_PUMP, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.IRON_ROD, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.IRON_SCREW, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.IRON_SMALL_GEAR, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.IRON_TURBINE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_AXE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_BOOTS, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_GEAR, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HELMET, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HOE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_NAIL, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PLATE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_ROD, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SCREW, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SWORD, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.PRESSURE_REGULATOR, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_AXE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_BOOTS, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_GEAR, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_HELMET, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_HOE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_NAIL, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_PLATE, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_ROD, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_SCREW, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.STEEL_SWORD, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.VALVE_ASSEMBLY, Models.GENERATED);
        itemModelGenerator.register(DeepworldItems.WOODEN_GEAR, Models.GENERATED);
    }
}
