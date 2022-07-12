package fr.dwightstudio.deepworld.common.blockentities.tanks;

import fr.dwightstudio.deepworld.common.blocks.IronTankBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleTankBlockEntity extends BlockEntity implements IFluidTank, IFluidHandler {

    private int capacity;
    private final int MAX_FILL_LEVEL;

    private FluidStack fluid;

    public SimpleTankBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, int capacity) {
        super(blockEntityType, blockPos, blockState);

        this.MAX_FILL_LEVEL = capacity / 1000;

        this.capacity = capacity;
        this.fluid = FluidStack.EMPTY;
    }

    @Override
    public @NotNull FluidStack getFluid() {
        return this.fluid.copy();
    }

    @Override
    public int getFluidAmount() {
        return this.fluid.getAmount();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        if (this.fluid.isEmpty()) return true;
        return this.fluid.isFluidEqual(stack);
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public @NotNull FluidStack getFluidInTank(int tank) {
        return this.fluid.copy();
    }

    @Override
    public int getTankCapacity(int tank) {
        return this.capacity;
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        if (this.fluid.isEmpty()) return true;
        return this.fluid.isFluidEqual(stack);
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {

        if (this.isEmpty()) {
            if (this.getBlockState().getValue(IronTankBlock.DOWN)) {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) this.level.getBlockEntity(new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ()));
                return blockEntity.fill(resource, action);
            } else {

            }
        } else if (this.getBlockState().getValue(IronTankBlock.UP)) {
            SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) this.level.getBlockEntity(getBlockPos().above());
            return blockEntity.canFill(resource);
        }

        int returnValue = canFill(resource);

        if (action == FluidAction.EXECUTE) {
            if (this.fluid.isEmpty()) {
                this.fluid = resource.copy();
                if (resource.getAmount() <= this.capacity) {
                    this.fluid.setAmount(resource.getAmount());
                } else {
                    this.fluid.setAmount(this.capacity);
                }
            } else {
                if (this.fluid.isFluidEqual(resource)) {
                    if (resource.getAmount() <= (this.capacity - this.fluid.getAmount())) {
                        this.fluid.grow(resource.getAmount());
                    } else {
                        this.fluid.grow(this.capacity - this.fluid.getAmount());
                    }
                }
            }
            sendUpdate();
        }

        return returnValue;
    }

    public int canFill(FluidStack resource) {

        if (!this.fluid.isFluidEqual(resource) && !this.isEmpty()) {
            return 0;
        } else {
            if (this.isEmpty()) {
                if (resource.getAmount() <= this.capacity) {
                    return resource.getAmount();
                } else {
                    return this.capacity;
                }
            } else {
                if (resource.getAmount() <= (this.capacity - this.fluid.getAmount())) {
                    return resource.getAmount();
                } else {
                    return this.capacity - this.fluid.getAmount();
                }
            }
        }

    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        FluidStack returnValue = canDrain(maxDrain);

        if (action == FluidAction.EXECUTE) {
            if (!this.isEmpty()) {
                if (this.fluid.getAmount() >= maxDrain) {
                    this.fluid.shrink(maxDrain);
                } else {
                    this.fluid = FluidStack.EMPTY;
                    this.fluid.setAmount(0);
                }
            }
            if (this.isEmpty()) {
                this.fluid = FluidStack.EMPTY;
                this.fluid.setAmount(0);
            }
            sendUpdate();
        }

        return returnValue;
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {

        FluidStack returnValue = canDrain(resource.getAmount());

        if (action == FluidAction.EXECUTE) {
            if (!this.isEmpty()) {
                if (this.fluid.getAmount() >= resource.getAmount()) {
                    this.fluid.shrink(resource.getAmount());
                } else {
                    this.fluid = FluidStack.EMPTY;
                    this.fluid.shrink(0);
                }
            }
            if (this.isEmpty()) {
                this.fluid = FluidStack.EMPTY;
                this.fluid.setAmount(0);
            }
            sendUpdate();
        }


        return returnValue;
    }

    public FluidStack canDrain(int drainQuantity) {
        if (!this.isEmpty()) {
            if (this.fluid.getAmount() >= drainQuantity) {
                return new FluidStack(this.fluid, drainQuantity);
            } else {
                return new FluidStack(this.fluid, this.fluid.getAmount());
            }
        } else {
            return new FluidStack(FluidStack.EMPTY, 0);
        }
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);

        this.fluid = FluidStack.loadFluidStackFromNBT(compoundTag.getCompound("Fluid"));
        this.capacity = compoundTag.getInt("Capacity");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        CompoundTag fluidStackTag = new CompoundTag();
        this.fluid.copy().writeToNBT(fluidStackTag);
        compoundTag.put("Fluid", fluidStackTag);
        compoundTag.putInt("Capacity", this.capacity);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    private void sendUpdate() {
        setChanged();
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 512);
    }

    public boolean isEmpty() {
        if (this.fluid.getAmount() <= 0) this.fluid = FluidStack.EMPTY;

        return this.fluid.isEmpty() || this.fluid.getAmount() <= 0;
    }

    public float getFluidLevel() {
        return (float)(this.fluid.getAmount() / 1000) / MAX_FILL_LEVEL;
    }
}
