package fr.dwightstudio.deepworld.common.tools;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public enum ToolsMaterialsTier implements Tier {

    STEEL(2, 850, 6.5F, 0.0F, 10, Ingredient.of(DeepworldItems.STEEL_INGOT.get())),

    OBSIDIAN_INFUSED_STEEL(3, 1900, 9.0F, 0.0F, 14, Ingredient.of(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.get()));

    private final int harvest_level;
    private final int durability;
    private final float efficiency;
    private final float attack_damage;
    private final int enchantability;
    private final Ingredient repair_material;

    ToolsMaterialsTier(int harvest_level, int durability, float efficiency, float attack_damage, int enchantability, Ingredient repair_material){
        this.harvest_level   = harvest_level;
        this.durability      = durability;
        this.efficiency      = efficiency;
        this.attack_damage   = attack_damage;
        this.enchantability  = enchantability;
        this.repair_material = repair_material;
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
        return attack_damage;
    }

    @Override
    public int getLevel() {
        return harvest_level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return repair_material;
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
