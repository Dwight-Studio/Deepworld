package fr.dwightstudio.deepworld.common.blockentities.tanks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class SimpleTankBlockEntity extends BlockEntity implements IFluidTank, IFluidHandler {

    private FluidStack fluid;
    private int capacity;
    private int fluidAmount;

    public SimpleTankBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, int capacity) {
        super(blockEntityType, blockPos, blockState);

        this.fluid = FluidStack.EMPTY;
        this.capacity = capacity;
        this.fluidAmount = 0;
    }

    @Override
    public @NotNull FluidStack getFluid() {
        return this.fluid;
    }

    @Override
    public int getFluidAmount() {
        return this.fluidAmount;
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
        return this.fluid;
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
        LogManager.getLogger().log(Level.DEBUG, "fluid : " + resource.getFluid() + " amount : " + resource.getAmount());

        if (action == FluidAction.EXECUTE) {
            if (this.fluid.isEmpty()) {
                this.fluid = resource;
                this.fluidAmount = resource.getAmount();
            } else {
                this.fluidAmount += resource.getAmount();
            }
        }

        return Math.min(resource.getAmount(), (this.capacity - this.fluidAmount));
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {

        if (action == FluidAction.EXECUTE) {
            if (!this.fluid.isEmpty()) {
                if (maxDrain <= this.fluidAmount) {
                    this.fluidAmount -= maxDrain;
                }
            }
        }

        if (maxDrain - this.fluidAmount >= 0) return new FluidStack(this.fluid, maxDrain);

        return new FluidStack(this.fluid, (maxDrain - this.fluidAmount));
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {

        if (action == FluidAction.EXECUTE) {
            if (!this.fluid.isEmpty()) {
                if (resource.getAmount() <= this.fluidAmount) {
                    this.fluidAmount -= resource.getAmount();
                }
            }
        }

        if (resource.getAmount() - this.fluidAmount >= 0) return new FluidStack(this.fluid, resource.getAmount());

        return new FluidStack(this.fluid, (resource.getAmount() - this.fluidAmount));
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);

        this.fluid = FluidStack.loadFluidStackFromNBT(compoundTag.getCompound("Fluid"));
        this.capacity = compoundTag.getInt("Capacity");
        this.fluidAmount = compoundTag.getInt("FluidAmount");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        CompoundTag fluidStackTag = new CompoundTag();
        this.fluid.writeToNBT(fluidStackTag);
        compoundTag.put("Fluid", fluidStackTag);
        compoundTag.putInt("Capacity", this.capacity);
        compoundTag.putInt("FluidAmount", this.fluidAmount);
    }
}
