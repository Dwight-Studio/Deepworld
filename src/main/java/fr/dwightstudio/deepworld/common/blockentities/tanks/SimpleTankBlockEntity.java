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

    private int CAPACITY;
    private final int MAX_FILL_LEVEL;

    private FluidStack fluid;
    private int fluidAmount;

    public SimpleTankBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, int capacity) {
        super(blockEntityType, blockPos, blockState);

        this.CAPACITY = capacity;
        this.MAX_FILL_LEVEL = capacity / 1000;

        this.fluid = FluidStack.EMPTY;
        this.fluidAmount = 0;
    }

    @Override
    public @NotNull FluidStack getFluid() {
        return this.fluid.copy();
    }

    @Override
    public int getFluidAmount() {
        return this.fluidAmount;
    }

    @Override
    public int getCapacity() {
        return this.CAPACITY;
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
        return this.CAPACITY;
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        if (this.fluid.isEmpty()) return true;
        return this.fluid.isFluidEqual(stack);
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {

        int returnValue = canFill(resource);

        if (action == FluidAction.EXECUTE) {
            if (this.fluid.isEmpty()) {
                this.fluid = resource.copy();
                if (resource.getAmount() <= this.CAPACITY) {
                    this.fluidAmount = resource.getAmount();
                } else {
                    this.fluidAmount = this.CAPACITY;
                }
            } else {
                if (this.fluid.isFluidEqual(resource)) {
                    if (resource.getAmount() <= (this.CAPACITY - this.fluidAmount)) {
                        this.fluidAmount += resource.getAmount();
                    } else {
                        this.fluidAmount += this.CAPACITY - this.fluidAmount;
                    }
                }
            }
            LogManager.getLogger().log(Level.DEBUG, "fluid : " + this.fluid.getFluid().getFluidType() + " amount : " + this.fluidAmount);
        }

        return returnValue;
    }

    public int canFill(FluidStack resource) {
        LogManager.getLogger().log(Level.DEBUG, this);
        if (!this.fluid.isFluidEqual(resource) && !this.isEmpty()) {
            return 0;
        } else {
            if (this.isEmpty()) {
                if (resource.getAmount() <= this.CAPACITY) {
                    return resource.getAmount();
                } else {
                    return this.CAPACITY;
                }
            } else {
                if (resource.getAmount() <= (this.CAPACITY - this.fluidAmount)) {
                    return resource.getAmount();
                } else {
                    return this.CAPACITY - this.fluidAmount;
                }
            }
        }
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        FluidStack returnValue = canDrain(maxDrain);

        if (action == FluidAction.EXECUTE) {
            if (!this.isEmpty()) {
                if (this.fluidAmount >= maxDrain) {
                    this.fluidAmount -= maxDrain;
                } else {
                    this.fluid = FluidStack.EMPTY;
                    this.fluidAmount = 0;
                }
            }
            LogManager.getLogger().log(Level.DEBUG, "fluid : " + this.fluid.getFluid().getFluidType() + " amount : " + this.fluidAmount);
        }

        return returnValue;
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {

        FluidStack returnValue = canDrain(resource.getAmount());

        if (action == FluidAction.EXECUTE) {
            if (!this.isEmpty()) {
                if (this.fluidAmount >= resource.getAmount()) {
                    this.fluidAmount -= resource.getAmount();
                } else {
                    this.fluid = FluidStack.EMPTY;
                    this.fluidAmount = 0;
                }
            }
            LogManager.getLogger().log(Level.DEBUG, "fluid : " + this.fluid.getFluid().getFluidType() + " amount : " + this.fluidAmount);
        }

        return returnValue;
    }

    public FluidStack canDrain(int drainQuantity) {
        if (!this.isEmpty()) {
            if (this.fluidAmount >= drainQuantity) {
                return new FluidStack(this.fluid, drainQuantity);
            } else {
                return new FluidStack(this.fluid, this.fluidAmount);
            }
        } else {
            return new FluidStack(FluidStack.EMPTY, 0);
        }
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);

        this.fluid = FluidStack.loadFluidStackFromNBT(compoundTag.getCompound("Fluid")).copy();
        this.CAPACITY = compoundTag.getInt("Capacity");
        this.fluidAmount = compoundTag.getInt("FluidAmount");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        CompoundTag fluidStackTag = new CompoundTag();
        this.fluid.copy().writeToNBT(fluidStackTag);
        compoundTag.put("Fluid", fluidStackTag);
        compoundTag.putInt("Capacity", this.CAPACITY);
        compoundTag.putInt("FluidAmount", this.fluidAmount);
    }

    public boolean isEmpty() {
        if (this.fluidAmount <= 0) this.fluid = FluidStack.EMPTY;

        return this.fluid.isEmpty() || this.fluidAmount <= 0;
    }


}
