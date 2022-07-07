package fr.dwightstudio.deepworld.common;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class DeepworldBlockTagsProvider extends BlockTagsProvider {

    public DeepworldBlockTagsProvider(DataGenerator p_126511_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(DeepworldBlocks.STEEL_BLOCK.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK.get());
    }
}
