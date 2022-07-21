/*
 *     ____           _       __    __     _____ __            ___
 *    / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *   / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *  / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 * /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                  /____/
 * Copyright (c) 2022-2022 Dwight Studio's Team <support@dwight-studio.fr>
 *
 * This Source Code From is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 */

package fr.dwightstudio.deepworld.common.blockentities.multiblocks;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

/**
 * Extends this class to use Multiblock mechanics
 */
public abstract class AbstractMultiblockHolder<H extends AbstractMultiblockHolder<H>> extends BlockEntity {

    private BlockPos parent;
    private BlockPos[] blocks;

    public AbstractMultiblockHolder(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);

        parent = blockPos;
        blocks = new BlockPos[]{parent};
    }

    /**
     * @return true if the MultiblockHolder is actually in a Multiblock structure
     */
    public final boolean isInMultiblock() {
        return blocks.length != 0 || isChild();
    }

    /**
     * Updates blockstate according to the new Multiblock data
     */
    public abstract void updateState();

    /**
     * Updates MultiblockHolder membership
     * @implNote If you want to get connected holder, {@see getConnectedHolders()}
     * @return the (new) parent of the MultiblockHolder, may be self
     */
    protected abstract H computeMultiblockPart();

    /**
     * Updates Multiblock internal structure
     * @implSpec Must be called when a block of the structure is updated (neighborChanged)
     */
    public final void updateMultiblock() {
        HashMap<H, ArrayList<BlockPos>> map = new HashMap<>();
        this.getConnectedHolders(arg -> true).stream().forEach(holder -> {
            H parent = holder.computeMultiblockPart();
            ArrayList<BlockPos> list = map.getOrDefault(parent, new ArrayList<>());
            holder.setParent(parent.getBlockPos());
            list.add(holder.getBlockPos());
            map.put(parent, list);
        });

        for (H parentHolder : map.keySet()) {
            parentHolder.setBlocks(map.getOrDefault(getHolderAt(level, parent), new ArrayList<>()).toArray(BlockPos[]::new));
        }

        this.multiblockTick();
    }

    /**
     * Gets all the blocks of the Multiblock
     * @return a list of BlockEntity objects
     */
    public List<H> getMultiblockHolders() {
        return isInMultiblock() ? Arrays.stream(this.blocks).map(blockPos -> this.getHolderAt(level, blockPos)).toList() : new ArrayList<>();
    }

    /**
     * Sets the blocks in the parent MultiblockHolder
     * @param blocks an Array of {@link BlockPos}
     */
    void setBlocks(BlockPos[] blocks) {
        this.blocks = blocks;
    }

    /**
     * Gets the parent of the Multiblock from the supplied implementation of {@link BlockGetter}
     * @return the parent MultiblockHolder
     */
    public final H getParent() {
        return getHolderAt(level, this.parent);
    }

    /**
     * Sets the parent of MultiblockHolder
     * @param parent a {@link BlockPos}
     */
    public void setParent(BlockPos parent) {
        this.parent = parent;
    }

    /**
     * @return true if this is a child, false otherwise
     */
    public final boolean isChild() {

        return !parent.equals(this.getBlockPos());
    }

    /**
     * Tests if a {@link BlockEntity} can connect to (join) this Multiblock, should return the same result if called on the other holder (x.canConnect(y) equals to y.canConnect(x))
     * @param blockEntity the {@link BlockEntity} to test
     * @return true if the block can connect, false otherwise
     */
    public abstract boolean canConnect(H blockEntity);

    /**
     * Updates the entire Multiblock logic {@see computeMultiblock to update structure}
     * @implNote Can split logic between child and parent
     */
    public abstract void multiblockTick();

    /**
     * Saves the Multiblock data in a {@link CompoundTag}
     * @param tag a {@link CompoundTag} object
     */
    public final void saveMultiblockData(CompoundTag tag) {
        CompoundTag multiblockTag = tag.getCompound("Multiblock");
        if (isChild()) {
            AbstractMultiblockHolder parent = getParent();
            CompoundTag parentTag = new CompoundTag();
            if (parent != null) {
                parentTag.putInt("x", parent.getBlockPos().getX());
                parentTag.putInt("y", parent.getBlockPos().getY());
                parentTag.putInt("z", parent.getBlockPos().getZ());

                multiblockTag.put("Parent", parentTag);
            } else {
                Deepworld.LOGGER.warn("Cannot save parent for MultiblockHolder at " + this.getBlockPos());
            }
        } else if (isInMultiblock()) {
            ListTag blockList = new ListTag();

            for (AbstractMultiblockHolder holder : getMultiblockHolders()) {
                CompoundTag blockTag = new CompoundTag();
                if (holder != null) {
                    blockTag.putInt("x", holder.getBlockPos().getX());
                    blockTag.putInt("y", holder.getBlockPos().getY());
                    blockTag.putInt("z", holder.getBlockPos().getZ());

                    blockList.add(blockTag);
                }
            }
            multiblockTag.put("Blocks", blockList);
        }

        saveMultiblockAdditionnalData(multiblockTag);

        tag.put("Multiblock", multiblockTag);
    }

    /**
     * Loads the Multiblock data from a {@link CompoundTag}
     * @param tag a {@link CompoundTag} object
     */
    public final void loadMultiblockData(CompoundTag tag) {
        CompoundTag multiblockTag = tag.getCompound("Multiblock");
        try {
            if (!multiblockTag.isEmpty()) {
                try {
                    loadMultiblockAdditionnalData(tag);
                } catch (Error error) {
                    Deepworld.LOGGER.warn("Corrupted custom data for MultiblockHolder at " + this + ", ignoring.");
                    Deepworld.LOGGER.trace(error.getStackTrace());
                }

                if (multiblockTag.contains("Parent")) {
                    CompoundTag parentTag = multiblockTag.getCompound("Parent");
                    if (!parentTag.isEmpty()) {
                        BlockPos blockPos = new BlockPos(parentTag.getInt("x"), parentTag.getInt("y"), parentTag.getInt("z"));
                        this.setParent(blockPos);
                    } else {
                        Deepworld.LOGGER.warn("Corrupted data for MultiblockHolder at " + this.getBlockPos());
                    }
                } else if (multiblockTag.contains("Blocks")) {
                    ListTag blocksList = multiblockTag.getList("Blocks", Tag.TAG_COMPOUND);
                    if (!blocksList.isEmpty()) {
                        ArrayList<BlockPos> blockPosList = new ArrayList<>();
                        blocksList.forEach(blockTag -> blockPosList.add(new BlockPos(((CompoundTag) blockTag).getInt("x"), ((CompoundTag) blockTag).getInt("y"), ((CompoundTag) blockTag).getInt("z"))));
                        this.setBlocks(blockPosList.toArray(new BlockPos[0]));
                    } else {
                        Deepworld.LOGGER.warn("Corrupted data for MultiblockHolder at " + this.getBlockPos());
                    }
                } else {
                    Deepworld.LOGGER.warn("Corrupted data for MultiblockHolder at " + this.getBlockPos());
                }
            }

        } catch (Error error) {
            Deepworld.LOGGER.warn("Corrupted data for MultiblockHolder at " + this.getBlockPos());
            Deepworld.LOGGER.trace(error.getStackTrace());
        }
    }

    /**
     * Saves the Multiblock custom data in a {@link CompoundTag}
     * @param tag a {@link CompoundTag} object
     */
    public void saveMultiblockAdditionnalData(CompoundTag tag) {}

    /**
     * Handle the customData of the Multiblock from a {@link CompoundTag}
     * @param tag a {@link CompoundTag} object
     */
    public void loadMultiblockAdditionnalData(CompoundTag tag) {}

    /**
     * Transfers parent properties to another MultiblockHolder
     * @throws IllegalAccessError when called on a non parent MultiblockHolder
     * @param newParent the new parent
     */
    public void transferParent(H newParent) {
        if (this.isChild()) throw new IllegalAccessError("Cannot transfer parent properties from non parent MultiblockHolder");
        
        for (H block : getMultiblockHolders()) {
            block.setParent(newParent.getBlockPos());
        }

        this.setParent(newParent.getBlockPos());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadMultiblockData(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveMultiblockData(tag);
    }

    public final List<H> getConnectedHolders(Predicate<?> predicate) {
        ArrayList<H> list = new ArrayList<>();
        list.add((H) this);
        recursiveGetConnectedHolders(list, predicate);

        return list;
    }

    void recursiveGetConnectedHolders(ArrayList<H> list, Predicate<?> predicate) {
        for (Direction dir : Direction.values()) {
            H holder = this.getHolderAt(level, this.getBlockPos().relative(dir));
            if (holder != null) {
                if (!list.contains(holder)) {
                    list.add(holder);
                    holder.recursiveGetConnectedHolders(list, predicate);
                }
            }
        }
    }

    @Nullable
    public H getHolderAt(BlockGetter level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        try {
            return (H) blockEntity;
        } catch (ClassCastException exception) {
            return null;
        }
    }
}
