package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.Deepworld;
import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DeepworldTagGenerator{

    public static class DeepworldItemTagGenerator extends FabricTagProvider<Item> {

        private static final TagKey<Item> OBSIDIAN_INFUSED_STEEL_INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier(Deepworld.MOD_ID, "obsidian_infused_steel_ingots"));
        private static final TagKey<Item> STEEL_INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier(Deepworld.MOD_ID,"steel_ingots"));

        public DeepworldItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.ITEM, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(STEEL_INGOTS).add(DeepworldItems.STEEL_INGOT);
            getOrCreateTagBuilder(OBSIDIAN_INFUSED_STEEL_INGOTS).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT);
        }
    }

    public static class DeepworldBlockTagGenerator extends FabricTagProvider<Block> {

        private static final TagKey<Block> OBSIDIAN_INFUSED_STEEL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(Deepworld.MOD_ID, "obsidian_infused_steel_blocks"));
        private static final TagKey<Block> STEEL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(Deepworld.MOD_ID,"steel_blocks"));

        public DeepworldBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.BLOCK, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(STEEL_BLOCKS).add(DeepworldBlocks.STEEL_BLOCK);
            getOrCreateTagBuilder(OBSIDIAN_INFUSED_STEEL_BLOCKS).add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);
        }
    }
}

