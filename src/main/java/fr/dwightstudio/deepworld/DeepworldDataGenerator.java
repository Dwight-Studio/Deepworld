package fr.dwightstudio.deepworld;

import fr.dwightstudio.deepworld.data.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DeepworldDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator){
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(DeepworldLootTableGenerator::new);
        pack.addProvider(DeepworldRecipeGenerator::new);
        pack.addProvider(DeepworldModelGenerator::new);
        pack.addProvider(DeepworldLangGenerator.DeepworldLangGeneratorEN::new);
        pack.addProvider(DeepworldLangGenerator.DeepworldLangGeneratorFR::new);
        pack.addProvider(DeepworldTagGenerator.DeepworldItemTagGenerator::new);
        pack.addProvider(DeepworldTagGenerator.DeepworldBlockTagGenerator::new);
    }

}
