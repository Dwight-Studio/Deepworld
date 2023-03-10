package fr.dwightstudio.deepworld.common.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldDataGenerator {

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();

        gen.addProvider(
                event.includeClient(),
                output -> new DeepworldBlockStateProvider(output, efh)
        );

        gen.addProvider(
                event.includeServer(),
                DeepworldRecipeProvider::new
        );
    }

}
