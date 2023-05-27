package fr.dwightstudio.deepworld.common.data;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blocks.machines.MachineBlock;
import fr.dwightstudio.deepworld.common.components.FrameComponent;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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
        this.dropMachine(DeepworldBlocks.WOODEN_LATHE.get());
        this.dropMachine(DeepworldBlocks.WOODEN_PRESS.get());
        this.dropMachine(DeepworldBlocks.WOODEN_GEAR_SHAPER.get());

        this.dropSelf(DeepworldBlocks.IRON_TANK.get());
        this.dropSelf(DeepworldBlocks.PIPE.get());
    }

    private LootPool.Builder getConstantPoolFor(ItemLike item, int count) {
        return this.applyExplosionCondition(item,LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ConstantValue.exactly(count)))));
    }

    protected void dropMachine(Block block) {
        MachineBlock machineBlock = (MachineBlock) block;

        LootTable.Builder lootTable = LootTable.lootTable();

        for (FrameComponent component : machineBlock.getComponents()) {
            lootTable.withPool(getConstantPoolFor(component.getItem(), 1));
        }

        lootTable.withPool(getConstantPoolFor(machineBlock.getFrame().asItem(),1));

        this.add(machineBlock, lootTable);
    }
}
