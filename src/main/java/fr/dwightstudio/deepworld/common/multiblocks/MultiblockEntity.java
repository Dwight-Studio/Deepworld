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

package fr.dwightstudio.deepworld.common.multiblocks;

import fr.dwightstudio.deepworld.common.registries.DeepworldMultiblocks;
import fr.dwightstudio.deepworld.common.utils.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class MultiblockEntity {

    private boolean dirty = false;
    private final UUID uuid;
    private final LevelAccessor level;
    private final Set<BlockPos> blockEntities;
    private final DeepworldMultiblocks type;


    private MultiblockEntity(UUID uuid, LevelAccessor level, DeepworldMultiblocks type) {
        this.uuid = uuid;
        this.level = level;
        this.type = type;
        blockEntities = new HashSet<>();
    }

    public MultiblockEntity(LevelAccessor level, DeepworldMultiblocks type) {
        this(UUID.randomUUID(), level, type);
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public void setDirty() {
        this.setDirty(true);
    }

    public UUID getUUID() {
        return uuid;
    }

    /**
     * @return true if all blocks are loaded, false otherwise
     */
    public boolean isLoaded() {
        final boolean[] flag = {true};
        blockEntities.forEach(blockPos -> flag[0] = flag[0] && level.hasChunkAt(blockPos));
        return flag[0];
    }

    /**
     * @return the list of the pos of the associated blocks
     */
    public List<BlockPos> getBlockEntitiesPos() {
        return new ArrayList<>(blockEntities);
    }

    private void internalUpdate() {
        if (isDirty()) {
            update();

            WorldUtils.getBlockEntities(level, getBlockEntitiesPos()).forEach(blockEntity -> {
                if (blockEntity.isRemoved()) {
                    getBlockEntitiesPos().remove(blockEntity.getBlockPos());
                }
                if (blockEntity instanceof MultiblockHolder holder) {
                    holder.onMultiblockUpdate(this);
                }
            });

            MultiblocksManager.getManager(level).setDirty();
        }
    }

    /**
     * Updates the multiblock internal mechanics, called when marked dirty
     */
    public abstract void update();

    public final @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        tag.putUUID("uuid", this.uuid);
        tag.putString("type", this.type.getLocation().toLanguageKey());
        ListTag listTag = new ListTag();
        for (BlockPos pos : blockEntities) {
            listTag.add(WorldUtils.savePos(new CompoundTag(), pos));
        }
        tag.put("blocks", listTag);
        saveAdditionnal(tag);
        return tag;
    }

    public final void load(@NotNull CompoundTag tag) {
        if (tag.contains("blocks")) {
            ListTag listTag = tag.getList("blocks", Tag.TAG_COMPOUND);
            listTag.forEach(blockTag -> blockEntities.add(WorldUtils.posFromTag(tag)));
        }
        loadAdditionnal(tag);
    }

    public abstract @NotNull CompoundTag saveAdditionnal(@NotNull CompoundTag tag);

    public abstract void loadAdditionnal(@NotNull CompoundTag tag);
}
