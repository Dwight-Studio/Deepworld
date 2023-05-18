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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

public class TestMultiblockEntity extends MultiblockEntity {
    public TestMultiblockEntity(LevelAccessor level) {
        super(level, DeepworldMultiblocks.TEST);
    }

    @Override
    public void update() {

    }

    @Override
    public @NotNull CompoundTag saveAdditionnal(@NotNull CompoundTag tag) {
        return null;
    }

    @Override
    public void loadAdditionnal(@NotNull CompoundTag tag) {

    }
}
