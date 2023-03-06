package fr.dwightstudio.deepworld.util;

import fr.dwightstudio.deepworld.Deepworld;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class DeepworldTags {

    public static class Blocks {

        public static final TagKey<Block> STEEL_BLOCKS = createCommonTag("steel_blocks");

        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Deepworld.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }

    }

    public static class Items {

        public static final TagKey<Item> STEEL_INGOTS = createCommonTag("steel_ingots");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Deepworld.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }

    }

}
