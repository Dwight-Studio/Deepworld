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

package fr.dwightstudio.deepworld.common.recipes.serializers;

import com.google.gson.JsonObject;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MachineRecipeSerializer implements RecipeSerializer<MachineRecipe> {

    private final RecipeType<? extends MachineRecipe> recipeType;
    private final String name;

    private MachineRecipeSerializer(RecipeType<MachineRecipe> recipeType, String name) {
        this.recipeType = recipeType;
        this.name = name;

    }

    public static RegistryObject<RecipeSerializer<MachineRecipe>> register(String name, RegistryObject<RecipeType<MachineRecipe>> recipeType) {
        return Deepworld.RECIPE_SERIALIZERS.register(name, () -> new MachineRecipeSerializer(recipeType.get(), name));
    }

    @Override
    public @NotNull MachineRecipe fromJson(@NotNull ResourceLocation resourceLocation, @NotNull JsonObject jsonObject) {
        Ingredient inputIngredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "ingredient"));
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
        int processTime = GsonHelper.getAsInt(jsonObject, "processTime");
        String machineTierString = GsonHelper.getAsString(jsonObject, "machineTier");

        int ingredientCount = 1;
        if (GsonHelper.isNumberValue(jsonObject, "ingredientsCount")) {
            ingredientCount = GsonHelper.getAsInt(jsonObject, "ingredientsCount");
        }

        MachineTier machineTier = MachineTier.valueOf(machineTierString.toUpperCase());

        return new MachineRecipe(recipeType, resourceLocation, inputIngredient, ingredientCount, result, processTime, machineTier);
    }

    @Override
    public @Nullable MachineRecipe fromNetwork(@NotNull ResourceLocation resourceLocation, @NotNull FriendlyByteBuf buf) {
        String type = buf.readUtf();
        int processTime = buf.readInt();
        Ingredient ingredient = Ingredient.fromNetwork(buf);
        int ingredientCount = buf.readInt();
        MachineTier machineTier = MachineTier.valueOf(buf.readUtf().toUpperCase());
        ItemStack result = buf.readItem();

        return new MachineRecipe(recipeType, resourceLocation, ingredient, ingredientCount, result, processTime, machineTier);
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull MachineRecipe machineRecipe) {
        buf.writeUtf(machineRecipe.getGroup());
        buf.writeInt(machineRecipe.getProcessTime());
        machineRecipe.getIngredient().toNetwork(buf);
        buf.writeInt(machineRecipe.getIngredientCount());
        buf.writeUtf(machineRecipe.getMachineTier().name());
        buf.writeItem(machineRecipe.getResultItem());
    }

    @Override
    public String toString() {
        return this.name;
    }
}
