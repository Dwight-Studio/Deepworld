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

package fr.dwightstudio.deepworld.common.multiblocks;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber()
public class MultiblocksManager extends SavedData {

    private static final Map<LevelAccessor, MultiblocksManager> CLIENT_INSTANCES = new HashMap<>();
    private static final String SAVE_FILE_NAME = Deepworld.MOD_ID + "_multiblocks";

    private Map<UUID, MultiblockEntity> entities;

    private MultiblocksManager() {

    }

    private MultiblocksManager(CompoundTag tag) {
        this();
        this.load(tag);
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        return tag;
    }

    public void load(CompoundTag tag) {

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getDataStorage().computeIfAbsent(MultiblocksManager::new, MultiblocksManager::new, SAVE_FILE_NAME);
        } else {
            CLIENT_INSTANCES.put(event.getLevel(), new MultiblocksManager());
        }
    }

    public static MultiblocksManager getManager(LevelAccessor level) {
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.getDataStorage().get(MultiblocksManager::new, SAVE_FILE_NAME);
        } else {
            return CLIENT_INSTANCES.getOrDefault(level, new MultiblocksManager());
        }
    }

    public void setMultiblockEntity(UUID uuid, MultiblockEntity entity) {
        entities.put(uuid,entity);
    }

    public MultiblockEntity getMultiblockEntity(UUID uuid) {
        return entities.get(uuid);
    }
}
