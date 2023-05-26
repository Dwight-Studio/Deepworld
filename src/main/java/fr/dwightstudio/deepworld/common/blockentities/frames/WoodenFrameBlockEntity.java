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

package fr.dwightstudio.deepworld.common.blockentities.frames;

import fr.dwightstudio.deepworld.common.blocks.frames.FrameBlock;
import fr.dwightstudio.deepworld.common.blocks.frames.WoodenFrameBlock;
import fr.dwightstudio.deepworld.common.components.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class WoodenFrameBlockEntity extends BlockEntity {

    private int covers = 0;

    private int primaryComponent = 0;
    private int secondaryComponent = 0;
    private int tertiaryComponent = 0;

    private boolean complete;

    public WoodenFrameBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_FRAME.get(), blockPos, blockState);
    }

    @Override
    public void load(@NotNull CompoundTag compound) {

        super.load(compound); // The super call is required to load the tiles location

        covers = compound.getInt("Cover");

        primaryComponent = compound.getInt("PrimaryComponent");
        secondaryComponent = compound.getInt("SecondaryComponent");
        tertiaryComponent = compound.getInt("TertiaryComponent");

        complete = compound.getBoolean("complete");
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound); // The super call is required to save the tiles location

        compound.putInt("cover", covers);

        compound.putInt("PrimaryComponent", primaryComponent);
        compound.putInt("SecondaryComponent", secondaryComponent);
        compound.putInt("TertiaryComponent", tertiaryComponent);

        compound.putBoolean("complete", complete);
    }

    public int getCovers() {
        return covers;
    }

    public boolean addCover() {
        if (covers >= 0 && covers < 6) {
            covers++;
        } else {
            return false;
        }

        if (covers == 6) {
            complete = true;
            sendParticules((ServerLevel) getLevel(), getBlockPos());
            updateBlockState();
        } else {
            updateBlockState();
        }
        return true;
    }

    private void updateBlockState() {
        assert this.level != null;
        getLevel().playSound(null, getBlockPos(), SoundEvents.ITEM_FRAME_PLACE, SoundSource.BLOCKS);
        BlockState currentBlockState = this.level.getBlockState(this.worldPosition);
        BlockState newBlockState = currentBlockState
                .setValue(FrameBlock.COVER, covers)
                .setValue(WoodenFrameBlock.PRIMARY_COMPONENT, primaryComponent)
                .setValue(WoodenFrameBlock.SECONDARY_COMPONENT, secondaryComponent)
                .setValue(WoodenFrameBlock.TERTIARY_COMPONENT, tertiaryComponent);
        if (!newBlockState.equals(currentBlockState)) {
            getLevel().setBlock(this.worldPosition, newBlockState, Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
        }
        sendUpdate();
    }

    private void applyCraft() {
        Block result = WoodenFrameComponent.getResultFromTile(this);
        BlockState state = result.defaultBlockState();

        if (state.getProperties().contains(HorizontalDirectionalBlock.FACING)) {
            assert this.level != null;
            state = state.setValue(HorizontalDirectionalBlock.FACING, this.level.getBlockState(this.worldPosition).getValue(FrameBlock.FACING));
        }

        this.level.setBlock(this.worldPosition, state, Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
        sendUpdate();
    }

    private void sendParticules(ServerLevel level, BlockPos blockPos) {
        for (int i = 0; i < 30; i++) {
            Random random = new Random();

            double x = random.nextDouble() + blockPos.getX();
            double y = random.nextDouble() + blockPos.getY();
            double z = random.nextDouble() + blockPos.getZ();

            int rand = random.nextInt(3);

            if (rand == 0) {
                x = Math.round(x);
            } else if (rand == 1) {
                y = Math.round(y);
            } else {
                z = Math.round(y);
            }

            level.sendParticles(ParticleTypes.COMPOSTER, x, y, z, 3, 0, 0, 0, 0.1);
        }
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        WoodenFrameBlockEntity woodenFrameBlockEntity = (WoodenFrameBlockEntity) blockEntity;

        if (woodenFrameBlockEntity.complete) {
            woodenFrameBlockEntity.applyCraft();
        }
    }

    // Component management (getters and setters)
    public int getPrimaryComponent() {
        return primaryComponent;
    }

    public void setPrimaryComponent(int primaryComponent) {
        this.primaryComponent = primaryComponent;
        updateBlockState();
    }

    public int getSecondaryComponent() {
        return secondaryComponent;
    }

    public void setSecondaryComponent(int secondaryComponent) {
        this.secondaryComponent = secondaryComponent;
        updateBlockState();
    }

    public int getTertiaryComponent() {
        return tertiaryComponent;
    }

    public void setTertiaryComponent(int tertiaryComponent) {
        this.tertiaryComponent = tertiaryComponent;
        updateBlockState();
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

}
