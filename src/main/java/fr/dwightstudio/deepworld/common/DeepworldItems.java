package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.item.ItemDeepworld;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Deepworld.MOD_ID)
public class DeepworldItems {

    // Construct all items
    public static final Item WOODEN_CASING = new ItemDeepworld();

    // Register items
    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(config(WOODEN_CASING, "wooden_casing"));
    }

    // Configure items
    public static Item config(Item item, String name) {
        return item.setTranslationKey(name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }
}
