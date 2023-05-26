package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DeepworldTags {

    private DeepworldTags() {
    }

    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(Deepworld.loc( name));
        }

        private static TagKey<Block> tagForge(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        public static final TagKey<Block> STEEL = tagForge("storage_blocks/steel");
        public static final TagKey<Block> OBSIDIAN_INFUSED_STEEL = tagForge("storage_blocks/obsidian_infused_steel");

        public static final TagKey<Block> MACHINES = tag("machines");
        public static final TagKey<Block> MACHINES_WOODEN = tag("machines/wooden");

    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(Deepworld.loc( name));
        }

        private static TagKey<Item> tagForge(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        // Ingots
        public static final TagKey<Item> INGOTS_STEEL = tagForge("ingots/steel");
        public static final TagKey<Item> INGOTS_OBSIDIAN_INFUSED_STEEL = tagForge("ingots/obsidian_infused_steel");

        // Gears
        public static final TagKey<Item> GEARS = tagForge("gears");
        public static final TagKey<Item> GEARS_WOODEN = tagForge("gears/wooden");
        public static final TagKey<Item> GEARS_IRON = tagForge("gears/iron");
        public static final TagKey<Item> GEARS_STEEL = tagForge("gears/steel");
        public static final TagKey<Item> GEARS_OBSIDIAN_INFUSED_STEEL = tagForge("gears/obsidian_infused_steel");

        // Rods
        public static final TagKey<Item> RODS = tagForge("rods");
        public static final TagKey<Item> RODS_IRON = tagForge("rods/iron");
        public static final TagKey<Item> RODS_STEEL = tagForge("rods/steel");
        public static final TagKey<Item> RODS_OBSIDIAN_INFUSED_STEEL = tagForge("rods/obsidian_infused_steel");

        // Plates
        public static final TagKey<Item> PLATES = tagForge("plates");
        public static final TagKey<Item> PLATES_IRON = tagForge("plates/iron");
        public static final TagKey<Item> PLATES_STEEL = tagForge("plates/steel");
        public static final TagKey<Item> PLATES_OBSIDIAN_INFUSED_STEEL = tagForge("plates/obsidian_infused_steel");

        // Screws
        public static final TagKey<Item> SCREWS = tagForge("screws");
        public static final TagKey<Item> SCREWS_IRON = tagForge("screws/iron");
        public static final TagKey<Item> SCREWS_STEEL = tagForge("screws/steel");
        public static final TagKey<Item> SCREWS_OBSIDIAN_INFUSED_STEEL = tagForge("screws/obsidian_infused_steel");

        // Nails
        public static final TagKey<Item> NAILS = tagForge("nails");
        public static final TagKey<Item> NAILS_IRON = tagForge("nails/iron");
        public static final TagKey<Item> NAILS_STEEL = tagForge("nails/steel");
        public static final TagKey<Item> NAILS_OBSIDIAN_INFUSED_STEEL = tagForge("nails/obsidian_infused_steel");

    }
}
