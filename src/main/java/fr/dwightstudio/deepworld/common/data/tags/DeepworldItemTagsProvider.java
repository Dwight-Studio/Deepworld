package fr.dwightstudio.deepworld.common.data.tags;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import fr.dwightstudio.deepworld.common.registries.DeepworldTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DeepworldItemTagsProvider extends ItemTagsProvider {
    public DeepworldItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> futureProvider, TagsProvider<Block> blockTagsProvider,@Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, futureProvider, blockTagsProvider, Deepworld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Items
        this.tag(DeepworldTags.Items.INGOTS_STEEL).add(DeepworldItems.STEEL_INGOT.getKey());
        this.tag(DeepworldTags.Items.INGOTS_OBSIDIAN_INFUSED_STEEL).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.getKey());
        
        this.tag(DeepworldTags.Items.GEARS_WOODEN).add(DeepworldItems.WOODEN_GEAR.getKey());
        this.tag(DeepworldTags.Items.GEARS_IRON).add(DeepworldItems.IRON_GEAR.getKey());
        this.tag(DeepworldTags.Items.GEARS_STEEL).add(DeepworldItems.STEEL_GEAR.getKey());
        this.tag(DeepworldTags.Items.GEARS_OBSIDIAN_INFUSED_STEEL).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_GEAR.getKey());
        
        this.tag(DeepworldTags.Items.RODS_IRON).add(DeepworldItems.IRON_ROD.getKey());
        this.tag(DeepworldTags.Items.RODS_STEEL).add(DeepworldItems.STEEL_ROD.getKey());
        this.tag(DeepworldTags.Items.RODS_OBSIDIAN_INFUSED_STEEL).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_ROD.getKey());
        
        this.tag(DeepworldTags.Items.PLATES_IRON).add(DeepworldItems.IRON_PLATE.getKey());
        this.tag(DeepworldTags.Items.PLATES_STEEL).add(DeepworldItems.STEEL_PLATE.getKey());
        this.tag(DeepworldTags.Items.PLATES_OBSIDIAN_INFUSED_STEEL).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PLATE.getKey());

        this.tag(DeepworldTags.Items.NAILS_IRON).add(DeepworldItems.IRON_NAIL.getKey());
        this.tag(DeepworldTags.Items.NAILS_STEEL).add(DeepworldItems.STEEL_NAIL.getKey());
        this.tag(DeepworldTags.Items.NAILS_OBSIDIAN_INFUSED_STEEL).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_NAIL.getKey());

        this.tag(DeepworldTags.Items.SCREWS_IRON).add(DeepworldItems.IRON_SCREW.getKey());
        this.tag(DeepworldTags.Items.SCREWS_STEEL).add(DeepworldItems.STEEL_SCREW.getKey());
        this.tag(DeepworldTags.Items.SCREWS_OBSIDIAN_INFUSED_STEEL).add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SCREW.getKey());
        
        
        // Tags
        this.tag(Tags.Items.INGOTS)
                .addTag(DeepworldTags.Items.INGOTS_STEEL)
                .addTag(DeepworldTags.Items.INGOTS_OBSIDIAN_INFUSED_STEEL);
        
        this.tag(DeepworldTags.Items.GEARS)
                .addTag(DeepworldTags.Items.GEARS_WOODEN)
                .addTag(DeepworldTags.Items.GEARS_IRON)
                .addTag(DeepworldTags.Items.GEARS_STEEL)
                .addTag(DeepworldTags.Items.GEARS_OBSIDIAN_INFUSED_STEEL);

        this.tag(DeepworldTags.Items.RODS)
                .addTag(DeepworldTags.Items.RODS_IRON)
                .addTag(DeepworldTags.Items.RODS_STEEL)
                .addTag(DeepworldTags.Items.RODS_OBSIDIAN_INFUSED_STEEL);

        this.tag(DeepworldTags.Items.PLATES)
                .addTag(DeepworldTags.Items.PLATES_IRON)
                .addTag(DeepworldTags.Items.PLATES_STEEL)
                .addTag(DeepworldTags.Items.PLATES_OBSIDIAN_INFUSED_STEEL);

        this.tag(DeepworldTags.Items.NAILS)
                .addTag(DeepworldTags.Items.NAILS_IRON)
                .addTag(DeepworldTags.Items.NAILS_STEEL)
                .addTag(DeepworldTags.Items.NAILS_OBSIDIAN_INFUSED_STEEL);

        this.tag(DeepworldTags.Items.SCREWS)
                .addTag(DeepworldTags.Items.SCREWS_IRON)
                .addTag(DeepworldTags.Items.SCREWS_STEEL)
                .addTag(DeepworldTags.Items.SCREWS_OBSIDIAN_INFUSED_STEEL);
    }
}
