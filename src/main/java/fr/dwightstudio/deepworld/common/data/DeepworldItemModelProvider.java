package fr.dwightstudio.deepworld.common.data;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.items.component.FrameItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.stream.Collectors;

public class DeepworldItemModelProvider extends ItemModelProvider {
    public DeepworldItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Deepworld.MOD_ID, existingFileHelper);
    }

    protected @NotNull Iterable<ResourceLocation> getKnownItems() {
        return Deepworld.ITEMS.getEntries().stream().map(RegistryObject::getId).collect(Collectors.toList());
    }

    @Override
    protected void registerModels() {
        Deepworld.ITEMS.getEntries().forEach(entry -> {
            if (entry.get() instanceof BlockItem || entry.get() instanceof FrameItem) {
                this.withExistingParent(entry.getId().getPath(), entry.getId().withPrefix(BLOCK_FOLDER + "/"));
            } else if (entry.get() instanceof TieredItem) {
                this.withExistingParent(entry.getId().getPath(), mcLoc("item/handheld")).texture("layer0",entry.getId().withPrefix(ITEM_FOLDER + "/"));
            } else {
                this.basicItem(entry.getId());
            }
        });


        for (ResourceLocation item : getKnownItems()) {
            if (!generatedModels.containsKey(item.withPrefix(ITEM_FOLDER + "/"))) {
                throw new IllegalStateException(String.format(Locale.ROOT, "Missing item model '%s' for '%s'", item.withPrefix(ITEM_FOLDER + "/"), item));
            }
        }
    }
}
