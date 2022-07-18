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

package fr.dwightstudio.deepworld.common.armors;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ArmorMaterialsTier implements ArmorMaterial {

    STEEL(Deepworld.MOD_ID + ":steel", 18, new int[]{3, 7, 6, 3}, 10,
            SoundEvents.ARMOR_EQUIP_IRON, 0.0F, Ingredient.of(DeepworldItems.STEEL_INGOT.get())),

    OBSIDIAN_INFUSED_STEEL(Deepworld.MOD_ID + ":obsidian_infused_steel", 54, new int[]{4, 9, 7, 4}, 10,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0F, Ingredient.of(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.get()));

    private static final int[] MAX_DAMAGE = new int[]{11, 16, 15, 13};
    private final String name;
    private final int max_damage_factor;
    private final int[] damage_reduction_amount;
    private final int enchantability;
    private final SoundEvent sound_event;
    private final float toughness;
    private final Ingredient repair_material;

    ArmorMaterialsTier(String name, int max_damage_factor, int[] damage_reduction_amount, int enchantability,
                       SoundEvent sound_event, float toughness, Ingredient repair_material) {
        this.name = name;
        this.max_damage_factor = max_damage_factor;
        this.damage_reduction_amount = damage_reduction_amount;
        this.enchantability = enchantability;
        this.sound_event = sound_event;
        this.toughness = toughness;
        this.repair_material = repair_material;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot type) {
        return MAX_DAMAGE[type.getIndex()] * this.max_damage_factor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot type) {
        return this.damage_reduction_amount[type.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound_event;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repair_material;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}