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

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.registries.DeepworldMultiblocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
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

@Mod.EventBusSubscriber(modid = Deepworld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MultiblocksManager extends SavedData {

    private static final Map<LevelAccessor, MultiblocksManager> CLIENT_INSTANCES = new HashMap<>();
    private static final String SAVE_FILE_NAME = Deepworld.MOD_ID + "_multiblocks";

    private LevelAccessor level;
    private Map<UUID, MultiblockEntity> entities;

    private MultiblocksManager(LevelAccessor level) {
        this.level = level;
    }

    private MultiblocksManager(LevelAccessor level, CompoundTag tag) {
        this(level);
        load(tag);
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        ListTag listTag = new ListTag();

        for (MultiblockEntity entity : entities.values()) {
            CompoundTag entityTag = new CompoundTag();
            entity.save(entityTag);
            listTag.add(entityTag);
        }
        tag.put("multiblocks", listTag);
        return tag;
    }

    public void load(CompoundTag tag) {
        if (tag.contains("multiblocks")) {
            ListTag listTag = tag.getList("multiblocks", Tag.TAG_COMPOUND);
            if (!listTag.isEmpty()) {
                for (Tag sEntityTag : listTag) {
                    if (sEntityTag instanceof CompoundTag entityTag && !entityTag.isEmpty()) {
                        if (entities.containsKey(entityTag.getUUID("uuid"))) {
                            entities.get(entityTag.getUUID("uuid")).load(entityTag);
                        } else {
                            MultiblockEntity multiblock = DeepworldMultiblocks.fromResourceLocation(new ResourceLocation(entityTag.getString("type"))).get(level);
                            multiblock.load(entityTag);
                            entities.put(multiblock.getUUID(), multiblock);
                        }
                    }
                }
            }
        } else {
            Deepworld.LOGGER.error("Cannot load multiblocks data.");
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            MultiblocksManager manager = serverLevel.getDataStorage().get(tag -> new MultiblocksManager(event.getLevel(), tag), SAVE_FILE_NAME);
            if (manager == null) {
                manager = new MultiblocksManager(event.getLevel());
                serverLevel.getDataStorage().set(SAVE_FILE_NAME, manager);
            }
        } else {
            CLIENT_INSTANCES.put(event.getLevel(), new MultiblocksManager(event.getLevel()));
        }
    }

    public static MultiblocksManager getManager(LevelAccessor level) {
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.getDataStorage().get(tag -> new MultiblocksManager(level, tag), SAVE_FILE_NAME);
        } else {
            return CLIENT_INSTANCES.getOrDefault(level, new MultiblocksManager(level));
        }
    }

    public void setMultiblockEntity(UUID uuid, MultiblockEntity entity) {
        entities.put(uuid, entity);
    }

    public MultiblockEntity getMultiblockEntity(UUID uuid) {
        return entities.get(uuid);
    }
}
