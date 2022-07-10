package fr.dwightstudio.deepworld.common.tools;

import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ToolsMaterialsTier implements Tier {

    STEEL(2, 850, 6.5F, 0.0F, 10, DeepworldItems.STEEL_INGOT),

    OBSIDIAN_INFUSED_STEEL(3, 1900, 9.0F, 0.0F, 14, DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT);

    private final int harvestLevel;
    private final int durability;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Item> itemSupplier;

    ToolsMaterialsTier(int harvestLevel, int durability, float efficiency, float attackDamage, int enchantability, Supplier<Item> itemSupplier) {
        this.harvestLevel = harvestLevel;
        this.durability = durability;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.itemSupplier = itemSupplier;
    }

    @Override
    public int getUses() {
        return durability;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(itemSupplier.get());
    }

    @Override
    public @NotNull TagKey<Block> getTag() {
        switch (this) {
            case STEEL -> {
                return BlockTags.NEEDS_IRON_TOOL;
            }
            case OBSIDIAN_INFUSED_STEEL -> {
                return BlockTags.NEEDS_DIAMOND_TOOL;
            }
        }
        return BlockTags.NEEDS_IRON_TOOL;
    }
}
