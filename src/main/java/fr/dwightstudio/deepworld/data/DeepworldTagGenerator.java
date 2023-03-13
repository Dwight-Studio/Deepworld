package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.Deepworld;
import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DeepworldTagGenerator{

    public static class DeepworldItemTagGenerator extends FabricTagProvider<Item> {

        private static final TagKey<Item> DEEPWORLD_OBSIDIAN_INFUSED_STEEL_INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier(Deepworld.MOD_ID, "obsidian_infused_steel_ingots"));
        private static final TagKey<Item> DEEPWORLD_STEEL_INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier(Deepworld.MOD_ID,"steel_ingots"));
        private static final TagKey<Item> DEEPWORLD_OBSIDIAN_INFUSED_STEEL_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(Deepworld.MOD_ID, "obsidian_infused_steel_blocks"));
        private static final TagKey<Item> DEEPWORLD_STEEL_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(Deepworld.MOD_ID, "steel_blocks"));

        private static final TagKey<Item> C_OBSIDIAN_INFUSED_STEEL_INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "obsidian_infused_steel_ingots"));
        private static final TagKey<Item> C_STEEL_INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier("c","steel_ingots"));
        private static final TagKey<Item> C_OBSIDIAN_INFUSED_STEEL_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "obsidian_infused_steel_blocks"));
        private static final TagKey<Item> C_STEEL_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_blocks"));
        private static final TagKey<Item> C_WOOD_STICKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wood_sticks"));
        private static final TagKey<Item> C_WOODEN_RODS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods"));

        public DeepworldItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.ITEM, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(DEEPWORLD_OBSIDIAN_INFUSED_STEEL_INGOTS).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT);
            getOrCreateTagBuilder(DEEPWORLD_STEEL_INGOTS).add(DeepworldItems.STEEL_INGOT);
            getOrCreateTagBuilder(DEEPWORLD_OBSIDIAN_INFUSED_STEEL_BLOCKS).add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK.asItem());
            getOrCreateTagBuilder(DEEPWORLD_STEEL_BLOCKS).add(DeepworldBlocks.STEEL_BLOCK.asItem());
            getOrCreateTagBuilder(C_OBSIDIAN_INFUSED_STEEL_INGOTS).addTag(DEEPWORLD_OBSIDIAN_INFUSED_STEEL_INGOTS);
            getOrCreateTagBuilder(C_STEEL_INGOTS).addTag(DEEPWORLD_STEEL_INGOTS);
            getOrCreateTagBuilder(C_OBSIDIAN_INFUSED_STEEL_BLOCKS).addTag(DEEPWORLD_OBSIDIAN_INFUSED_STEEL_BLOCKS);
            getOrCreateTagBuilder(C_STEEL_BLOCKS).addTag(DEEPWORLD_STEEL_BLOCKS);
            getOrCreateTagBuilder(C_WOOD_STICKS).add(Items.STICK);
            getOrCreateTagBuilder(C_WOODEN_RODS).add(Items.STICK);
        }
    }

    public static class DeepworldBlockTagGenerator extends FabricTagProvider<Block> {

        private static final TagKey<Block> DEEPWORLD_OBSIDIAN_INFUSED_STEEL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(Deepworld.MOD_ID, "obsidian_infused_steel_blocks"));
        private static final TagKey<Block> DEEPWORLD_STEEL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(Deepworld.MOD_ID,"steel_blocks"));

        private static final TagKey<Block> C_OBSIDIAN_INFUSED_STEEL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "obsidian_infused_steel_blocks"));
        private static final TagKey<Block> C_STEEL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("c","steel_blocks"));

        private static final TagKey<Block> FABRIC_NEEDS_TOOL_LEVEL = TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4"));

        private static final TagKey<Block> MINECRAFT_NEEDS_DIAMOND_TOOL = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "needs_diamond_tool"));
        private static final TagKey<Block> MINECRAFT_NEEDS_IRON_TOOL = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "needs_iron_tool"));
        private static final TagKey<Block> MINECRAFT_NEEDS_STONE_TOOL = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "needs_stone_tool"));
        private static final TagKey<Block> MINECRAFT_MINEABLE_AXE = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/axe"));
        private static final TagKey<Block> MINECRAFT_MINEABLE_HOE = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/hoe"));
        private static final TagKey<Block> MINECRAFT_MINEABLE_PICKAXE = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/pickaxe"));
        private static final TagKey<Block> MINECRAFT_MINEABLE_SHOVEL = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/shovel"));

        public DeepworldBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.BLOCK, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(DEEPWORLD_STEEL_BLOCKS).add(DeepworldBlocks.STEEL_BLOCK);
            getOrCreateTagBuilder(DEEPWORLD_OBSIDIAN_INFUSED_STEEL_BLOCKS).add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);
            getOrCreateTagBuilder(C_OBSIDIAN_INFUSED_STEEL_BLOCKS).addTag(DEEPWORLD_OBSIDIAN_INFUSED_STEEL_BLOCKS);
            getOrCreateTagBuilder(C_STEEL_BLOCKS).addTag(DEEPWORLD_STEEL_BLOCKS);
            getOrCreateTagBuilder(FABRIC_NEEDS_TOOL_LEVEL);
            getOrCreateTagBuilder(MINECRAFT_NEEDS_DIAMOND_TOOL).add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);
            getOrCreateTagBuilder(MINECRAFT_NEEDS_IRON_TOOL).add(DeepworldBlocks.STEEL_BLOCK);
            getOrCreateTagBuilder(MINECRAFT_NEEDS_STONE_TOOL);
            getOrCreateTagBuilder(MINECRAFT_MINEABLE_AXE);
            getOrCreateTagBuilder(MINECRAFT_MINEABLE_HOE);
            getOrCreateTagBuilder(MINECRAFT_MINEABLE_PICKAXE).add(DeepworldBlocks.STEEL_BLOCK).add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);
            getOrCreateTagBuilder(MINECRAFT_MINEABLE_SHOVEL);
        }
    }
}

