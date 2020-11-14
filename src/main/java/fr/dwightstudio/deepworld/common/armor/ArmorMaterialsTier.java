package fr.dwightstudio.deepworld.common.armor;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ArmorMaterialsTier implements IArmorMaterial {

    STEEL(Deepworld.MOD_ID + ":steel", 18, new int[] {3, 7, 6, 3}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {
        return Ingredient.fromItems(DeepworldItems.STEEL_INGOT);
    }),

    OBSIDIAN_INFUSED_STEEL(Deepworld.MOD_ID + ":obsidian_infused_steel", 54, new int[] {4, 9, 7, 4}, 10,
    SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {
        return Ingredient.fromItems(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT);
    });

    private static final int[] MAX_DAMAGE = new int[] {11, 16, 15, 13};
    private final String name;
    private final int max_damage_factor;
    private final int[] damage_reduction_amount;
    private final int enchantability;
    private final SoundEvent sound_event;
    private final float toughness;
    private final Supplier<Ingredient> repair_material;

    ArmorMaterialsTier(String name, int max_damage_factor, int[] damage_reduction_amount, int enchantability,
                       SoundEvent sound_event, float toughness, Supplier<Ingredient> repair_material) {
        this.name = name;
        this.max_damage_factor = max_damage_factor;
        this.damage_reduction_amount = damage_reduction_amount;
        this.enchantability = enchantability;
        this.sound_event = sound_event;
        this.toughness = toughness;
        this.repair_material = repair_material;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE[slotIn.getIndex()] * this.max_damage_factor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damage_reduction_amount[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.sound_event;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repair_material.get();
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
}