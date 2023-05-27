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

package fr.dwightstudio.deepworld.common.components;

import fr.dwightstudio.deepworld.common.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.blocks.machines.WoodenMachineBlock;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public enum WoodenFrameComponent implements FrameComponent {

    // Components
    SIMPLE_PRESSING_CHAMBER(
            DeepworldItems.SIMPLE_PRESSING_CHAMBER::get,
            ComponentClass.PRIMARY,
            new ComponentMachine[]{DeepworldBlocks.WOODEN_PRESS::get}
    ),
    SIMPLE_CUTTER(
            DeepworldItems.SIMPLE_CUTTER::get,
            ComponentClass.PRIMARY,
            new ComponentMachine[]{DeepworldBlocks.WOODEN_GEAR_SHAPER::get}
    ),
    SIMPLE_TURNING_MECHANISM(
            DeepworldItems.SIMPLE_TURNING_MECHANISM::get,
            ComponentClass.PRIMARY,
            new ComponentMachine[]{DeepworldBlocks.WOODEN_LATHE::get}
    ),

    WOODEN_GEARBOX(
            DeepworldItems.WOODEN_GEARBOX::get,
            ComponentClass.SECONDARY,
            new ComponentMachine[]{DeepworldBlocks.WOODEN_PRESS::get, DeepworldBlocks.WOODEN_GEAR_SHAPER::get, DeepworldBlocks.WOODEN_LATHE::get}
    ),
    WOODEN_CRANK(
            DeepworldItems.WOODEN_CRANK::get,
            ComponentClass.TERTIARY,
            new ComponentMachine[]{DeepworldBlocks.WOODEN_PRESS::get, DeepworldBlocks.WOODEN_GEAR_SHAPER::get, DeepworldBlocks.WOODEN_LATHE::get}
    );

    // Var
    private final ComponentItem item;
    private final ComponentClass componentClass;
    private final ComponentMachine[] WoodenMachineBlocks;
    private final int ID;

    // Static nested class to store LastData.lastID
    private static class LastData {
        private static int[] lastID = {0, 0, 0};
    }

    WoodenFrameComponent(ComponentItem item, ComponentClass componentClass, ComponentMachine[] WoodenMachineBlocks) {
        this.item = item;
        this.componentClass = componentClass;
        this.WoodenMachineBlocks = WoodenMachineBlocks;
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
            if (component.getComponentClass().equals(componentClass) && component.getID() == ID) {
                return component;
            }
        }

        return null;
    }

    public static WoodenFrameComponent[] getByMachine(WoodenMachineBlock sWoodenMachineBlock) {
        WoodenFrameComponent[] rtn = {null, null, null};

        for (WoodenFrameComponent component : WoodenFrameComponent.values()) {
            for (WoodenMachineBlock WoodenMachineBlock : component.getMachineBlocks()) {
                if (WoodenMachineBlock.equals(sWoodenMachineBlock)) {
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

        for (Block PrimaryWoodenMachineBlock : c1.getMachineBlocks()) {
            for (Block SecondaryWoodenMachineBlock : c2.getMachineBlocks()) {
                for (Block TertiaryWoodenMachineBlock : c3.getMachineBlocks()) {
                    if (PrimaryWoodenMachineBlock == SecondaryWoodenMachineBlock && SecondaryWoodenMachineBlock == TertiaryWoodenMachineBlock)
                        return PrimaryWoodenMachineBlock;
                }
            }
        }
        return null;
    }

    public String getName() {
        return name();
    }

    public Item getItem() {
        return item.get();
    }

    public ComponentClass getComponentClass() {
        return this.componentClass;
    }

    public WoodenMachineBlock[] getMachineBlocks() {
        WoodenMachineBlock[] rtn = new WoodenMachineBlock[WoodenMachineBlocks.length];

        int i = 0;
        for (ComponentMachine WoodenMachineBlock : WoodenMachineBlocks) {
            rtn[i] = WoodenMachineBlock.get();
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
        default WoodenMachineBlock get() {
            return (WoodenMachineBlock) getBlock();
        }
        
        Block getBlock();
    }
}
