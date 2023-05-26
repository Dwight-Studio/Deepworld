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

package fr.dwightstudio.deepworld.common.items.component;

import fr.dwightstudio.deepworld.common.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.components.WoodenFrameComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WoodenFrameComponentItem extends FrameItem {

    private final WoodenFrameComponent component;

    public WoodenFrameComponentItem(WoodenFrameComponent component) {
        super(new Properties());
        this.component = component;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockEntity aTileEntity = context.getLevel().getBlockEntity(context.getClickedPos());

        if (aTileEntity instanceof WoodenFrameBlockEntity tileEntity) {

            switch (getComponent().getComponentClass()) {
                case PRIMARY:
                    if (!(tileEntity.getPrimaryComponent() == 0)) {
                        return InteractionResult.FAIL;
                    }
                    break;
                case SECONDARY:
                    if (!(tileEntity.getSecondaryComponent() == 0)) {
                        return InteractionResult.FAIL;
                    }
                    break;
                case TERTIARY:
                    if (!(tileEntity.getTertiaryComponent() == 0)) {
                        return InteractionResult.FAIL;
                    }
                    break;
                default:
                    return InteractionResult.FAIL;

            }

            switch (getComponent().getComponentClass()) {
                case PRIMARY:
                    tileEntity.setPrimaryComponent(getComponent().getID());
                    break;
                case SECONDARY:
                    tileEntity.setSecondaryComponent(getComponent().getID());
                    break;
                case TERTIARY:
                    tileEntity.setTertiaryComponent(getComponent().getID());
                    break;
                default:
                    return InteractionResult.FAIL;

            }

            if (!context.getPlayer().isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }

    // Get the component (to override)
    public WoodenFrameComponent getComponent() {
        return component;
    }
}
