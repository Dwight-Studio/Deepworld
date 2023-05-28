/*
 *       ____           _       __    __     _____ __            ___
 *      / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *     / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *    / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 *   /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                    /____/
 *   Copyright (c) 2022-2023 Dwight Studio's Team <support@dwight-studio.fr>
 *
 *   This Source Code From is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 *
 */

package fr.dwightstudio.deepworld.common.blockentities.tanks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TankBlockEntity extends BlockEntity implements IFluidTank, IFluidHandler {

    private int capacity;
    private final int MAX_FILL_LEVEL;
    private FluidStack fluid;


    public TankBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, int capacity) {
        super(blockEntityType, blockPos, blockState);

        this.MAX_FILL_LEVEL = capacity / 1000;

        this.capacity = capacity;
        this.fluid = FluidStack.EMPTY.copy();
    }

    @Override
    public @NotNull FluidStack getFluid() {
        return this.fluid.copy();
    }

    @Override
    public int getFluidAmount() {
        return isEmpty() ? 0 : this.fluid.getAmount();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return true;
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return true;
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
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        return getParentTank().fillAbove(resource, action);
    }
    
    protected int fillAbove(FluidStack resource, IFluidHandler.FluidAction action) {
        int rtn = 0;
        FluidStack sResource = resource.copy();
        TankBlockEntity nextTank = this;
        
        while (!sResource.isEmpty() && nextTank != null && nextTank.canFill(sResource)) {
            if ((nextTank.getCapacity() - nextTank.getFluidAmount()) < sResource.getAmount()) {
                int amountFilled = nextTank.getCapacity() - nextTank.getFluidAmount();
                rtn += amountFilled;
                sResource.shrink(amountFilled);
                if (action.execute()) {
                    if (nextTank.isEmpty()) {
                        nextTank.fluid = resource.copy();
                        nextTank.fluid.setAmount(amountFilled);
                    } else {
                        nextTank.fluid.grow(amountFilled);
                    }
                    nextTank.sendUpdate();
                }
            } else {
                rtn += sResource.getAmount();
                if (action.execute()) {
                    if (nextTank.isEmpty()) {
                        nextTank.fluid = resource.copy();
                    } else {
                        nextTank.fluid.grow(sResource.getAmount());
                    }
                    nextTank.sendUpdate();
                }
                sResource = FluidStack.EMPTY.copy();
            }
            
            nextTank = nextTank.getNextTank();
        }

        return rtn;
    }


    @Override
    public @NotNull FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        int rtn = 0;
        int amount = maxDrain;

        TankBlockEntity previousTank = getChildTank();

        while (amount != 0 && previousTank != null) {
            if (!previousTank.isEmpty()) {
                if (previousTank.getFluidAmount() < amount) {
                    int amountDrained = previousTank.getFluidAmount();
                    rtn += amountDrained;
                    if (action.execute()) {
                        previousTank.clear();
                        previousTank.sendUpdate();
                    }
                    amount -= amountDrained;
                } else {
                    rtn += amount;
                    if (action.execute()) {
                        previousTank.fluid.shrink(amount);
                        previousTank.sendUpdate();
                    }
                    amount = 0;
                }
            }

            previousTank = previousTank.getPreviousTank();
        }

        return new FluidStack(Fluids.WATER, rtn);
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        return drain(resource.getAmount(), action);
    }

    public void rearrangeFluid() {
        int amount = 0;
        TankBlockEntity nextTank = this;
        TankBlockEntity parent = getParentTank();
        FluidStack nFluid = parent.getFluid().copy();

        while (nextTank != null && isCompatible(parent)) {
            if (nFluid.isEmpty() && !nextTank.getFluid().isEmpty()) nFluid = nextTank.getFluid().copy();
            amount += nextTank.getFluidAmount();
            nextTank.clear();

            nextTank = nextTank.getNextTank();
        }

        nFluid.setAmount(amount);
        parent.fill(nFluid, FluidAction.EXECUTE);
    }

    public TankBlockEntity getParentTank() {
        if (getLevel() == null || !(getLevel().getBlockEntity(getBlockPos().below()) instanceof TankBlockEntity simpleTank)) return this;
        TankBlockEntity parent = simpleTank.getParentTank();
        return parent.isCompatible(this) ? parent : this;
    }

    public @Nullable TankBlockEntity getPreviousTank() {
        if (getLevel() == null || !(getLevel().getBlockEntity(getBlockPos().below()) instanceof TankBlockEntity simpleTank)) return null;
        return simpleTank.isCompatible(getParentTank()) ? simpleTank : null;
    }

    public TankBlockEntity getChildTank() {
        if (getLevel() == null || !(getLevel().getBlockEntity(getBlockPos().above()) instanceof TankBlockEntity simpleTank)) return this;
        TankBlockEntity child = simpleTank.getChildTank();
        return child.isCompatible(getParentTank()) ? child : this;
    }

    public @Nullable TankBlockEntity getNextTank() {
        if (getLevel() == null || !(getLevel().getBlockEntity(getBlockPos().above()) instanceof TankBlockEntity simpleTank)) return null;
        return simpleTank.isCompatible(getParentTank()) ? simpleTank : null;
    }

    public boolean canConnect(TankBlockEntity simpleTank) {
        return getParentTank().isCompatible(simpleTank.getParentTank());
    }

    public boolean isCompatible(TankBlockEntity simpleTank) {
        return simpleTank.isEmpty() || this.isEmpty() || simpleTank.getFluid().isFluidEqual(this.getFluid());
    }

    public boolean canFill(FluidStack resource) {
        return isEmpty() || getFluid().isFluidEqual(resource);
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
        if (this.fluid.getAmount() <= 0) this.fluid = FluidStack.EMPTY.copy();
        return this.fluid.isEmpty() || this.fluid.getAmount() <= 0;
    }

    public boolean isFull() {
        return this.getCapacity() == this.getFluidAmount();
    }

    public void clear() {
        this.fluid = FluidStack.EMPTY.copy();
        sendUpdate();
    }

    public float getFluidLevel() {
        return (float) (this.fluid.getAmount() / 1000) / MAX_FILL_LEVEL;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.FLUID_HANDLER)
            return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap);
    }
}
