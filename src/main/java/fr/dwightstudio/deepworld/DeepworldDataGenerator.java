package fr.dwightstudio.deepworld;

import fr.dwightstudio.deepworld.data.DeepworldLootTableGenerator;
import fr.dwightstudio.deepworld.data.DeepworldModelProvider;
import fr.dwightstudio.deepworld.data.DeepworldRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DeepworldDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator){
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(DeepworldLootTableGenerator::new);
        pack.addProvider(DeepworldRecipeGenerator::new);
        pack.addProvider(DeepworldModelProvider::new);
    }

}
