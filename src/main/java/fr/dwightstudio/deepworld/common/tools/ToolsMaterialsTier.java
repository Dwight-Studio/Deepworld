package fr.dwightstudio.deepworld.common.tools;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ToolsMaterialsTier implements IItemTier {

    STEEL(2, 850, 6.5F, 0.0F, 10, () -> {
        return Ingredient.fromItems(DeepworldItems.STEEL_INGOT);
    }),

    OBSIDIAN_INFUSED_STEEL(3, 1900, 9.0F, 0.0F, 14, () -> {
        return Ingredient.fromItems(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT);
    });

    private final int harvest_level;
    private final int durability;
    private final float efficiency;
    private final float attack_damage;
    private final int enchantability;
    private final Supplier<Ingredient> repair_material;

    ToolsMaterialsTier(int harvest_level, int durability, float efficiency, float attack_damage, int enchantability, Supplier<Ingredient> repair_material){
        this.harvest_level = harvest_level;
        this.durability = durability;
        this.efficiency = efficiency;
        this.attack_damage = attack_damage;
        this.enchantability = enchantability;
        this.repair_material = repair_material;
    }

    @Override
    public int getMaxUses() {
        return durability;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attack_damage;
    }

    @Override
    public int getHarvestLevel() {
        return harvest_level;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repair_material.get();
    }
}
