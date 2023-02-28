package fr.dwightstudio.deepworld.item;

import fr.dwightstudio.deepworld.Deepworld;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DeepworldItemGroup {

    public static ItemGroup DEEPWORLD;

    public static void registerItemGroups(){
        DEEPWORLD = FabricItemGroup.builder(new Identifier(Deepworld.MOD_ID, "deepworld"))
                .displayName(Text.translatable("itemgroup.deepworld"))
                .icon(() -> new ItemStack(DeepworldItems.IRON_PLATE)).build();
    }

}
