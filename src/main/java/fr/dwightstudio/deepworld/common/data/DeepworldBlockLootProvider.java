package fr.dwightstudio.deepworld.common.data;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DeepworldBlockLootProvider extends BlockLootSubProvider {

    private static final Set<Item> EXPLOSION_RESISTANT = new HashSet<>(); // Stream.of(...).map(RegistryObject::get).collect(Collectors.toSet());

    public DeepworldBlockLootProvider() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Deepworld.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    @Override
    protected void generate() {
        this.dropSelf(DeepworldBlocks.STEEL_BLOCK.get());
        this.dropSelf(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK.get());

        this.dropSelf(DeepworldBlocks.WOODEN_FRAME.get());
        this.add(DeepworldBlocks.WOODEN_LATHE.get(), noDrop());
        this.add(DeepworldBlocks.WOODEN_PRESS.get(), noDrop());
        this.add(DeepworldBlocks.WOODEN_GEAR_SHAPER.get(), noDrop());

        this.dropSelf(DeepworldBlocks.IRON_TANK.get());
        this.dropSelf(DeepworldBlocks.PIPE.get());
    }
}
