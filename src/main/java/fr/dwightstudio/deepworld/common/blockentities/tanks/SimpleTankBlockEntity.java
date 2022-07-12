package fr.dwightstudio.deepworld.common.blockentities.tanks;

import fr.dwightstudio.deepworld.common.blocks.tanks.IronTankBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
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
        return this.getCapacity();
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return true;
    }

    public boolean canConnect(FluidStack resource) {
        if (this.isEmpty()) {
            if (this.getBlockState().getValue(IronTankBlock.DOWN)) {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) this.level.getBlockEntity(getBlockPos().below());
                if (blockEntity == null) return false;
                if (blockEntity.isEmpty()) {
                    return blockEntity.canConnect(resource);
                } else {
                    return blockEntity.getFluid().isFluidEqual(resource);
                }
            } else {
                return true;
            }
        } else {
            return this.getFluid().isFluidEqual(resource);
        }
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if (this.isEmpty()) {
            if (this.getBlockState().getValue(IronTankBlock.DOWN)) {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) this.level.getBlockEntity(getBlockPos().below());
                if (blockEntity.isFull() || (!blockEntity.getFluid().isFluidEqual(resource) && !blockEntity.isEmpty())) {
                    return applyFill(resource, action);
                } else {
                    return blockEntity.fill(resource, action);
                }
            } else {
                return applyFill(resource, action);
            }
        } else if (this.willOverflow(resource)) {
            if (this.getBlockState().getValue(IronTankBlock.UP)) {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) this.level.getBlockEntity(getBlockPos().above());
                if (blockEntity == null || !blockEntity.getFluid().isFluidEqual(resource) && !blockEntity.isEmpty()) return 0;
                int amount = applyFill(resource, action);
                resource.shrink(amount);
                return blockEntity.fill(resource, action) + amount;
            } else {
                return applyFill(resource, action);
            }
        } else {
            return applyFill(resource, action);
        }
    }


    private int applyFill(FluidStack resource, IFluidHandler.FluidAction action) {
        int returnValue = canFill(resource);

        if (action == FluidAction.EXECUTE) {
            if (this.fluid.isEmpty()) {
                this.fluid = resource.copy();
                if (resource.getAmount() <= this.getCapacity()) {
                    this.fluid.setAmount(resource.getAmount());
                } else {
                    this.fluid.setAmount(this.getCapacity());
                }
            } else {
                if (this.fluid.isFluidEqual(resource)) {
                    if (resource.getAmount() <= (this.getCapacity() - this.fluid.getAmount())) {
                        this.fluid.grow(resource.getAmount());
                    } else {
                        this.fluid.grow(this.getCapacity() - this.fluid.getAmount());
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
                if (resource.getAmount() <= this.getCapacity()) {
                    return resource.getAmount();
                } else {
                    return this.getCapacity();
                }
            } else {
                if (resource.getAmount() <= (this.getCapacity() - this.getFluid().getAmount())) {
                    return resource.getAmount();
                } else {
                    return this.getCapacity() - this.fluid.getAmount();
                }
            }
        }
    }


    @Override
    public @NotNull FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        FluidStack returnValue = canDrain(maxDrain);

        if (willDry(maxDrain)) {
            if (this.getBlockState().getValue(IronTankBlock.DOWN)) {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) this.level.getBlockEntity(getBlockPos().below());
                int amount = applyDrain(maxDrain, action).getAmount();
                maxDrain -= amount;
                FluidStack fluidStack = blockEntity.drain(maxDrain, action);
                fluidStack.grow(amount);
                return fluidStack;
            } else {
                return applyDrain(maxDrain, action);
            }
        } else if (this.isFull()) {
            if (this.getBlockState().getValue(IronTankBlock.UP)) {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) this.level.getBlockEntity(getBlockPos().above());
                if (blockEntity.isEmpty()) {
                    return applyDrain(maxDrain, action);
                } else {
                    return blockEntity.drain(maxDrain, action);
                }
            } else {
                return applyDrain(maxDrain, action);
            }
        } else {
            return applyDrain(maxDrain, action);
        }
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        if (getFluid().isFluidEqual(resource)) {
            return drain(resource.getAmount(), action);
        } else {
            return FluidStack.EMPTY;
        }

    }

    private FluidStack applyDrain(int maxDrain, IFluidHandler.FluidAction action) {
        FluidStack returnValue = canDrain(maxDrain);

        if (action == FluidAction.EXECUTE) {
            if (!this.isEmpty()) {
                if (this.fluid.getAmount() >= maxDrain) {
                    this.fluid.shrink(maxDrain);
                } else {
                    this.fluid = FluidStack.EMPTY;
                }
            }
            if (this.isEmpty()) {
                this.fluid = FluidStack.EMPTY;
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
        this.getFluid().copy().writeToNBT(fluidStackTag);
        compoundTag.put("Fluid", fluidStackTag);
        compoundTag.putInt("Capacity", this.getCapacity());
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
        BlockState newBlockState = getBlockState();
        if (this.getFluid().getFluid().defaultFluidState().createLegacyBlock().getBlock().getLightEmission(this.getFluid().getFluid().defaultFluidState().createLegacyBlock(), level, getBlockPos()) > 0) {
             newBlockState = newBlockState.setValue(BlockStateProperties.LIT, true);
        } else {
            newBlockState = newBlockState.setValue(BlockStateProperties.LIT, false);
        }
        getLevel().setBlock(getBlockPos(), newBlockState, 2);
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 512);
    }

    public boolean isEmpty() {
        if (this.fluid.getAmount() <= 0) this.fluid = FluidStack.EMPTY;

        return this.fluid.isEmpty() || this.fluid.getAmount() <= 0;
    }

    public boolean isFull() {
        return this.getCapacity() == this.getFluidAmount();
    }

    public boolean willOverflow(FluidStack resource) {
        return resource.getAmount() + this.getFluid().getAmount() > this.getCapacity();
    }

    public boolean willDry(int resource) {
        return this.getFluid().getAmount() - resource < 0;
    }

    public float getFluidLevel() {
        return (float)(this.fluid.getAmount() / 1000) / MAX_FILL_LEVEL;
    }

    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && (side == Direction.DOWN || side == Direction.UP)) return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap, side);
    }
}
