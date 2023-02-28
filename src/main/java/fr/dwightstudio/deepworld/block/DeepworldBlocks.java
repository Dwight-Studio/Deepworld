package fr.dwightstudio.deepworld.block;

import fr.dwightstudio.deepworld.Deepworld;
import fr.dwightstudio.deepworld.item.DeepworldItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class DeepworldBlocks {

    // Blocks
    public static final Block STEEL_BLOCK = registerBlock("steel_block", new Block(FabricBlockSettings.of(Material.METAL).strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).requiresTool()), DeepworldItemGroup.DEEPWORLD);

    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(Deepworld.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        Item item = Registry.register(Registries.ITEM, new Identifier(Deepworld.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks(){
        Deepworld.LOGGER.info("Registering Mod Blocks for " + Deepworld.MOD_ID);
    }

}
