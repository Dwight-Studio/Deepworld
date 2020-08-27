package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.item.ItemDeepworld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Deepworld.MOD_ID)
public class DeepworldItems {

    // Declare items
    //public static final Item WOODEN_CASING = new ItemDeepworld();
    public static final Item WOODEN_CASING = new ItemBlock(DeepworldBlocks.WOODEN_CASING);
    public static final Item IRON_CASING = new ItemBlock(DeepworldBlocks.IRON_CASING);

    // Register items
    public static void registerItems(IForgeRegistry<Item> registry) {
        //registry.register(prepare(WOODEN_CASING, "wooden_casing"));
    }

    // Register ItemBlock
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.register(DeepworldItems.prepare(WOODEN_CASING, "wooden_casing"));
        registry.register(DeepworldItems.prepare(IRON_CASING, "iron_casing"));
    }

    // Prepare items
    public static Item prepare(Item item, String name) {
        return item.setTranslationKey(name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }
}
