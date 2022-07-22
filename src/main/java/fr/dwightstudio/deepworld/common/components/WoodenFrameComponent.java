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

package fr.dwightstudio.deepworld.common.components;

import fr.dwightstudio.deepworld.common.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public enum WoodenFrameComponent {
    // Components
    SIMPLE_PRESSING_CHAMBER(
            DeepworldItems.SIMPLE_PRESSING_CHAMBER::get,
            ComponentClass.PRIMARY,
            new ComponentMachine[]{() -> DeepworldBlocks.WOODEN_PRESS.get()}
    ),
    WOODEN_GEARBOX(
            DeepworldItems.WOODEN_GEARBOX::get,
            ComponentClass.SECONDARY,
            new ComponentMachine[]{() -> DeepworldBlocks.WOODEN_PRESS.get(), () -> DeepworldBlocks.WOODEN_GEAR_SHAPER.get()}
    ),
    WOODEN_CRANK(
            DeepworldItems.WOODEN_CRANK::get,
            ComponentClass.TERTIARY,
            new ComponentMachine[]{() -> DeepworldBlocks.WOODEN_PRESS.get(), () -> DeepworldBlocks.WOODEN_GEAR_SHAPER.get()}
    ),
    SIMPLE_CUTTER(
            DeepworldItems.SIMPLE_CUTTER::get,
            ComponentClass.PRIMARY,
            new ComponentMachine[]{() -> DeepworldBlocks.WOODEN_GEAR_SHAPER.get()}
    ),
    SIMPLE_LEFT_PART_HOLDER(
            DeepworldItems.SIMPLE_LEFT_PART_HOLDER::get,
            ComponentClass.SECONDARY,
            new ComponentMachine[]{() -> DeepworldBlocks.WOODEN_LATHE.get()}
    ),
    SIMPLE_RIGHT_PART_HOLDER(
            DeepworldItems.SIMPLE_RIGHT_PART_HOLDER::get,
            ComponentClass.TERTIARY,
            new ComponentMachine[]{() -> DeepworldBlocks.WOODEN_LATHE.get()}
    );

    // Var
    private final ComponentItem item;
    private final ComponentClass componentClass;
    private final ComponentMachine[] machineBlocks;
    private final int ID;

    // Static nested class to store LastData.lastID
    private static class LastData {
        private static int[] lastID = {0, 0, 0};
    }

    WoodenFrameComponent(ComponentItem item, ComponentClass componentClass, ComponentMachine[] machineBlocks) {
        this.item = item;
        this.componentClass = componentClass;
        this.machineBlocks = machineBlocks;
        ID = createID(this.componentClass);
    }

    private int createID(ComponentClass componentClass) {
        LastData.lastID[componentClass.getIndex()] = LastData.lastID[componentClass.getIndex()] + 1;
        return LastData.lastID[componentClass.getIndex()];
    }

    public static int getLastID(ComponentClass componentClass) {
        return LastData.lastID[componentClass.getIndex()];
    }

    public static WoodenFrameComponent getByID(int ID, ComponentClass componentClass) {
        for (WoodenFrameComponent component : WoodenFrameComponent.values()) {
            if (component.getComponentClass() == componentClass && component.getID() == ID) {
                return component;
            }
        }

        return null;
    }

    public static WoodenFrameComponent[] getByMachine(Block sMachineBlock) {
        WoodenFrameComponent[] rtn = {null, null, null};

        for (WoodenFrameComponent component : WoodenFrameComponent.values()) {
            for (Block machineBlock : component.getMachineBlocks()) {
                if (machineBlock == sMachineBlock) {
                    rtn[component.getComponentClass().getIndex()] = component;
                    break;
                }
            }
        }

        return rtn;
    }

    public static Block getResultFromTile(WoodenFrameBlockEntity tile) {
        return getResult(tile.getPrimaryComponent(), tile.getSecondaryComponent(), tile.getTertiaryComponent());
    }

    public static Block getResult(int ci1, int ci2, int ci3) {
        WoodenFrameComponent c1 = getByID(ci1, ComponentClass.PRIMARY);
        WoodenFrameComponent c2 = getByID(ci2, ComponentClass.SECONDARY);
        WoodenFrameComponent c3 = getByID(ci3, ComponentClass.TERTIARY);

        return getResult(c1, c2, c3);
    }

    public static Block getResult(WoodenFrameComponent c1, WoodenFrameComponent c2, WoodenFrameComponent c3) {
        if (c1 == null || c2 == null || c3 == null) return null;

        for (Block PrimaryMachineBlock : c1.getMachineBlocks()) {
            for (Block SecondaryMachineBlock : c2.getMachineBlocks()) {
                for (Block TertiaryMachineBlock : c3.getMachineBlocks()) {
                    if (PrimaryMachineBlock == SecondaryMachineBlock && SecondaryMachineBlock == TertiaryMachineBlock)
                        return PrimaryMachineBlock;
                }
            }
        }
        return null;
    }

    public Item getItem() {
        return item.get();
    }

    public ComponentClass getComponentClass() {
        return this.componentClass;
    }

    public Block[] getMachineBlocks() {
        Block[] rtn = new Block[machineBlocks.length];

        int i = 0;
        for (ComponentMachine machineBlock : machineBlocks) {
            rtn[i] = machineBlock.get();
            i++;
        }

        return rtn;
    }

    public int getID() {
        return this.ID;
    }

    private interface ComponentItem {
        Item get();
    }

    private interface ComponentMachine {
        Block get();
    }
}
