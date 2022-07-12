package fr.dwightstudio.deepworld.common.blockentities.tanks;

import fr.dwightstudio.deepworld.common.blocks.tanks.IronTankBlock;
import fr.dwightstudio.deepworld.common.blockentities.multiblocks.AbstractMultiblockHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleTankBlockEntity extends AbstractMultiblockHolder implements IFluidTank, IFluidHandler {

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

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        FluidStack rtn = resource.copy();
        int amount = resource.getAmount();

        if (this.isChild()) {
            return ((SimpleTankBlockEntity) this.getParent()).fill(resource, action);
        } else {
            Arrays.stream(this.getMultiblockHolders()).sorted(Comparator.comparingInt(holder -> holder.getBlockPos().getY())).forEachOrdered(holder -> {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) holder;
                rtn.shrink(blockEntity.applyFill(rtn, action));
            });
        }

        return amount - resource.getAmount();
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
        int amount = maxDrain;
        FluidStack rtn = new FluidStack(this.getFluid(), maxDrain);

        if (this.isChild()) {
            return ((SimpleTankBlockEntity) this.getParent()).drain(maxDrain, action);
        } else {
            Arrays.stream(this.getMultiblockHolders()).sorted(Comparator.comparingInt(holder -> holder.getBlockPos().getY())).forEachOrdered(holder -> {
                SimpleTankBlockEntity blockEntity = (SimpleTankBlockEntity) holder;
                rtn.shrink(blockEntity.applyDrain(rtn.getAmount(), action).getAmount());
            });
        }

        return new FluidStack(rtn.getFluid(), amount - rtn.getAmount());
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
    public void updateState() {}

    @Override
    public AbstractMultiblockHolder computeMultiblockPart() {
        AbstractMultiblockHolder[] entities = this.getConnectedHolders(holder -> holder instanceof SimpleTankBlockEntity
                && ((SimpleTankBlockEntity) holder).getBlockPos().getX() == this.getBlockPos().getX()
                && ((SimpleTankBlockEntity) holder).getBlockPos().getZ() == this.getBlockPos().getZ());
        return Arrays.stream(entities).min(Comparator.comparingInt(holder -> holder.getBlockPos().getY())).get();
    }

    @Override
    public boolean canJoin(AbstractMultiblockHolder blockEntity) {
        return ((SimpleTankBlockEntity) blockEntity.getParent()).getFluid().isFluidEqual(((SimpleTankBlockEntity) this.getParent()).getFluid());
    }

    @Override
    public void multiblockTick() {
        if (!this.isChild()) {
            int amount = Stream.of(this.getMultiblockHolders()).map(holder -> (SimpleTankBlockEntity) holder).mapToInt(SimpleTankBlockEntity::getFluidAmount).sum();
            FluidStack nFluid = new FluidStack(this.getFluid().getFluid(), amount);
            Stream.of(this.getMultiblockHolders()).map(holder -> (SimpleTankBlockEntity) holder).forEach(SimpleTankBlockEntity::clear);
            this.fill(nFluid, FluidAction.EXECUTE);
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

    public void clear() {
        this.fluid = FluidStack.EMPTY;
    }

    public float getFluidLevel() {
        return (float)(this.fluid.getAmount() / 1000) / MAX_FILL_LEVEL;
    }

    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && (side == Direction.DOWN || side == Direction.UP)) return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap, side);
    }
}
