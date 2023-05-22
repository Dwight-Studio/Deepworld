package fr.dwightstudio.deepworld.common.data.tags;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.registries.DeepworldTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DeepworldBlockTagsProvider extends BlockTagsProvider {
    public DeepworldBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> futureProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, futureProvider, Deepworld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Blocks
        this.tag(DeepworldTags.Blocks.STEEL).add(DeepworldBlocks.STEEL_BLOCK.getKey());
        this.tag(DeepworldTags.Blocks.OBSIDIAN_INFUSED_STEEL).add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK.getKey());

        this.tag(DeepworldTags.Blocks.MACHINES_WOODEN)
                .add(DeepworldBlocks.WOODEN_FRAME.getKey())
                .add(DeepworldBlocks.WOODEN_LATHE.getKey())
                .add(DeepworldBlocks.WOODEN_GEAR_SHAPER.getKey())
                .add(DeepworldBlocks.WOODEN_PRESS.getKey());


        // Tags
        this.tag(DeepworldTags.Blocks.MACHINES).addTag(DeepworldTags.Blocks.MACHINES_WOODEN);

        this.tag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(DeepworldTags.Blocks.STEEL)
                .addTag(DeepworldTags.Blocks.OBSIDIAN_INFUSED_STEEL);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(DeepworldTags.Blocks.STEEL)
                .addTag(DeepworldTags.Blocks.OBSIDIAN_INFUSED_STEEL);

        this.tag(BlockTags.NEEDS_IRON_TOOL).addTag(DeepworldTags.Blocks.STEEL);
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).addTag(DeepworldTags.Blocks.OBSIDIAN_INFUSED_STEEL);

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(DeepworldTags.Blocks.MACHINES_WOODEN);
    }
}
