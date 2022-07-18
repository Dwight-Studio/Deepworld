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

package fr.dwightstudio.deepworld.client.sounds.machines;

import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenMachineBlockEntity;
import fr.dwightstudio.deepworld.common.registries.DeepworldSoundEvents;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WoodenMachineSoundInstance extends AbstractTickableSoundInstance {

    private final WoodenMachineBlockEntity blockEntity;

    public WoodenMachineSoundInstance(WoodenMachineBlockEntity blockEntity) {
        super(DeepworldSoundEvents.WOODEN_MACHINE.get(), SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
        this.blockEntity = blockEntity;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.0F;
        this.attenuation = Attenuation.LINEAR; //TODO: Implement attenuation
        updateBlockPos();
    }

    @Override
    public void tick() {
        if (!this.isStopped() && !blockEntity.isRemoved()) {
            this.volume = (float) blockEntity.inertia / (float) WoodenMachineBlockEntity.MAX_INERTIA;
        } else {
            this.stop();
        }
    }

    private void updateBlockPos() {
        this.x = blockEntity.getBlockPos().getX();
        this.y = blockEntity.getBlockPos().getY();
        this.z = blockEntity.getBlockPos().getZ();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }
}
