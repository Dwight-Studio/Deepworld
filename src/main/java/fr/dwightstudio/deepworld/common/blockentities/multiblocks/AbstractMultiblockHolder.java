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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Extends this class to use Multiblock mechanics
 */
public abstract class AbstractMultiblockHolder extends BlockEntity {

    private BlockPos parent;
    private BlockPos[] blocks = new BlockPos[0];

    public AbstractMultiblockHolder(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);

        parent = blockPos;
    }

    /**
     * @return true if the MultiblockHolder is actually in a Multiblock structure
     */
    public final boolean isInMultiblock() {
        return blocks.length != 0;
    }

    /**
     * Updates blockstate according to the new Multiblock data
     */
    public abstract void updateState();

    /**
     * Updates Multiblock structure for each block in the structure
     * @implNote If you want to get connected holder, {@see getConnectedHolders()}
     * @return the (new) parent of the MultiblockHolder, may be self
     * */
    public abstract AbstractMultiblockHolder computeMultiblock();

    /**
     * Gets all the blocks of the Multiblock
     * @return an Array of BlockEntity objects
     */
    public AbstractMultiblockHolder[] getMultiblockHolders() {
        return isInMultiblock() ? Arrays.stream(this.blocks).map(blockPos -> (AbstractMultiblockHolder) this.getLevel().getBlockEntity(blockPos)).toArray(AbstractMultiblockHolder[]::new) : new AbstractMultiblockHolder[0];
    }

    /**
     * Sets the blocks in the parent MultiblockHolder
     * @param blocks an Array of {@link BlockPos}
     */
    private void setBlocks(BlockPos[] blocks) {
        this.blocks = blocks;
    }

    /**
     * Gets the parent of the Multiblock from the supplied implementation of {@link BlockGetter}
     * @return the parent MultiblockHolder
     */
    public final AbstractMultiblockHolder getParent() {
        return (AbstractMultiblockHolder) this.getLevel().getBlockEntity(this.parent);
    }

    /**
     * Sets the parent of MultiblockHolder
     * @param parent a {@link BlockPos}
     */
    private void setParent(BlockPos parent) {
        this.parent = parent;
    }

    /**
     * @return true if this is a child, false otherwise
     */
    public final boolean isChild() {
        return parent != this.getBlockPos();
    }

    /**
     * Tests if a {@link BlockEntity} can join this Multiblock
     * @param blockEntity the {@link BlockEntity} to test
     * @return true if the block can be joined, false otherwise
     */
    public abstract boolean canJoin(AbstractMultiblockHolder blockEntity);

    /**
     * Updates the entire Multiblock logic {@see computeMultiblock to update structure}
     * @implNote Can split logic between child and parent
     */
    public abstract void multiblockUpdate();

    /**
     * Saves the Multiblock data in a {@link CompoundTag}
     * @param tag a {@link CompoundTag} object
     * @return a {@link CompoundTag} containing the Multiblock data
     */
    public final void saveMultiblockData(CompoundTag tag) {
        if (isChild()) {
            AbstractMultiblockHolder parent = getParent();
            CompoundTag parentTag = new CompoundTag();
            if (parent != null) {
                parentTag.putInt("x", parent.getBlockPos().getX());
                parentTag.putInt("y", parent.getBlockPos().getY());
                parentTag.putInt("z", parent.getBlockPos().getZ());

                tag.put("Parent", parentTag);
            } else {
                Deepworld.LOGGER.warn("Cannot save parent for MultiblockHolder at " + this.getBlockPos());
            }
        } else {
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
            tag.put("Blocks", blockList);
        }

        saveMultiblockAdditionnalData(tag);

        tag.put("Multiblock", tag);
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
            } else {
                Deepworld.LOGGER.warn("Corrupted data for MultiblockHolder at " + this.getBlockPos());
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
    public void transferParent(AbstractMultiblockHolder newParent) {
        if (this.isChild()) throw new IllegalAccessError("Cannot transfer parent properties from non parent MultiblockHolder");
        
        for (AbstractMultiblockHolder block : getMultiblockHolders()) {
            block.setParent(newParent.getBlockPos());
        }

        this.setParent(newParent.getBlockPos());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadMultiblockData(tag);
        updateState();
        multiblockUpdate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveMultiblockData(tag);
    }

    public final <T> AbstractMultiblockHolder[] getConnectedHolders(Predicate<?> predicate) {
        ArrayList<AbstractMultiblockHolder> list = new ArrayList<>();
        list.add(this);
        recursiveGetConnectedHolders(list, predicate);

        return list.toArray(AbstractMultiblockHolder[]::new);
    }

    private void recursiveGetConnectedHolders(ArrayList<AbstractMultiblockHolder> list, Predicate<?> predicate) {
        for (Direction dir : Direction.values()) {
            BlockEntity blockEntity = this.getLevel().getBlockEntity(this.getBlockPos().relative(dir));
            if (blockEntity instanceof AbstractMultiblockHolder) {
                AbstractMultiblockHolder holder = (AbstractMultiblockHolder) blockEntity;
                if (!list.contains(holder)) {
                    list.add(holder);
                    holder.recursiveGetConnectedHolders(list, predicate);
                }
            }
        }
    }
}
