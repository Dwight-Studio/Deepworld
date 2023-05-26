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

package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.multiblocks.MultiblockEntity;
import fr.dwightstudio.deepworld.common.multiblocks.TestMultiblockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;

import java.util.Arrays;
import java.util.Optional;

public enum DeepworldMultiblocks {
    TEST("test", TestMultiblockEntity::new);
    private final ResourceLocation location;
    private final MultiblockEntitySupplier supplier;

    DeepworldMultiblocks(String location, MultiblockEntitySupplier supplier) {
        this.location = Deepworld.loc( location);
        this.supplier = supplier;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public MultiblockEntity get(LevelAccessor level) {
        return supplier.get(level);
    }

    public interface MultiblockEntitySupplier {
        MultiblockEntity get(LevelAccessor level);
    }

    public static DeepworldMultiblocks fromResourceLocation(ResourceLocation resourceLocation) {
        Optional<DeepworldMultiblocks> opt = Arrays.stream(values()).filter(deepworldMultiblocks -> deepworldMultiblocks.getLocation().compareNamespaced(resourceLocation) == 0).findFirst();
        return opt.orElseThrow();
    }
}
