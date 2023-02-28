package fr.dwightstudio.deepworld.item;

import fr.dwightstudio.deepworld.Deepworld;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DeepworldItems {

    public static final Item IRONPLATE = registerItem("iron_plate", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Deepworld.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup(){
        addToItemGroup(ItemGroups.INGREDIENTS, IRONPLATE);
    }

    private static void addToItemGroup(ItemGroup group, Item item){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems(){
        Deepworld.LOGGER.info("Registering Mod Items for " + Deepworld.MOD_ID);

        addItemsToItemGroup();
    }

}
