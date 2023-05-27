package fr.dwightstudio.deepworld.common.data.recipes;

import fr.dwightstudio.deepworld.common.data.DeepworldRecipeProvider;
import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import fr.dwightstudio.deepworld.common.registries.DeepworldRecipeSerializers;
import fr.dwightstudio.deepworld.common.registries.DeepworldTags;
import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static fr.dwightstudio.deepworld.common.data.DeepworldRecipeProvider.has;
import static fr.dwightstudio.deepworld.common.data.DeepworldRecipeProvider.nail;

public class WoodenTier {

    private WoodenTier() {}

    public static void generate(@NotNull Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeepworldItems.WOODEN_GEAR.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .define('#', ItemTags.PLANKS)
                .unlockedBy("has_material", has(ItemTags.PLANKS))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.WOODEN_GEAR.get().toString()));

        nail(exporter, Tags.Items.NUGGETS_IRON, DeepworldItems.IRON_NAIL.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeepworldItems.WOODEN_CRANK.get())
                .pattern("##")
                .pattern("# ")
                .pattern("##")
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_material", has(Tags.Items.RODS_WOODEN))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.WOODEN_CRANK.get().toString()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeepworldItems.WOODEN_GEARBOX.get())
                .pattern("PPP")
                .pattern("G|G")
                .pattern("PPP")
                .define('|', Tags.Items.RODS_WOODEN)
                .define('P', ItemTags.PLANKS)
                .define('G', DeepworldTags.Items.GEARS_WOODEN)
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_plank", has(ItemTags.PLANKS))
                .unlockedBy("has_gear", has(DeepworldTags.Items.GEARS_WOODEN))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.WOODEN_GEARBOX.get().toString()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeepworldItems.SIMPLE_PRESSING_CHAMBER.get())
                .pattern("GPG")
                .pattern("C C")
                .pattern("GPG")
                .define('C', Tags.Items.COBBLESTONE)
                .define('P', Items.PISTON)
                .define('G', DeepworldTags.Items.GEARS_WOODEN)
                .unlockedBy("has_cobblestone", has(Tags.Items.COBBLESTONE))
                .unlockedBy("has_piston", has(Items.PISTON))
                .unlockedBy("has_gear", has(DeepworldTags.Items.GEARS_WOODEN))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.SIMPLE_PRESSING_CHAMBER.get().toString()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeepworldItems.SIMPLE_CUTTER.get())
                .pattern("G|G")
                .pattern("G G")
                .pattern("G|G")
                .define('|', Tags.Items.RODS_WOODEN)
                .define('G', DeepworldTags.Items.GEARS_WOODEN)
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_gear", has(DeepworldTags.Items.GEARS_WOODEN))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.SIMPLE_CUTTER.get().toString()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeepworldItems.WOODEN_FRAME.get())
                .pattern("P|P")
                .pattern("|B|")
                .pattern("P|P")
                .define('|', Tags.Items.RODS_WOODEN)
                .define('P', ItemTags.PLANKS)
                .define('B', Tags.Items.SLIMEBALLS)
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_plank", has(ItemTags.PLANKS))
                .unlockedBy("has_slimeball", has(Tags.Items.SLIMEBALLS))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.WOODEN_FRAME.get().toString()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeepworldItems.WOODEN_CASE_PANEL.get())
                .pattern("NPN")
                .pattern("PPP")
                .pattern("NPN")
                .define('P', ItemTags.PLANKS)
                .define('N', DeepworldTags.Items.NAILS_IRON)
                .unlockedBy("has_plank", has(ItemTags.PLANKS))
                .unlockedBy("has_nail", has(DeepworldTags.Items.NAILS_IRON))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.WOODEN_CASE_PANEL.get().toString()));

        MachineRecipeBuilder.process(RecipeCategory.MISC, DeepworldItems.WOODEN_GEAR.get())
                .machine(DeepworldRecipeSerializers.GEAR_SHAPING.get(), MachineTier.WOOD)
                .ingredient(ItemTags.PLANKS, 4)
                .processingTime(300)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.WOODEN_GEAR.get().toString()));

        MachineRecipeBuilder.process(RecipeCategory.MISC, DeepworldItems.IRON_GEAR.get())
                .machine(DeepworldRecipeSerializers.GEAR_SHAPING.get(), MachineTier.WOOD)
                .ingredient(Tags.Items.INGOTS_IRON, 4)
                .processingTime(500)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.IRON_GEAR.get().toString()));

        MachineRecipeBuilder.process(RecipeCategory.MISC, Items.STICK)
                .machine(DeepworldRecipeSerializers.LATHING.get(), MachineTier.WOOD)
                .ingredient(ItemTags.PLANKS, 1)
                .processingTime(100)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(Items.STICK.toString()));

        MachineRecipeBuilder.process(RecipeCategory.MISC, DeepworldItems.IRON_ROD.get())
                .machine(DeepworldRecipeSerializers.LATHING.get(), MachineTier.WOOD)
                .ingredient(Tags.Items.INGOTS_IRON, 1)
                .processingTime(500)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.IRON_ROD.get().toString()));


        MachineRecipeBuilder.process(RecipeCategory.MISC, DeepworldItems.IRON_PLATE.get())
                .machine(DeepworldRecipeSerializers.PRESSING.get(), MachineTier.WOOD)
                .ingredient(Tags.Items.INGOTS_IRON, 1)
                .processingTime(500)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(DeepworldItems.IRON_PLATE.get().toString()));

    }
}
