package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.Deepworld;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DeepworldWorldGenerator extends FabricDynamicRegistryProvider {
    public DeepworldWorldGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        //HERE GOES PROBABLY NOTHING
    }

    @Override
    public String getName() {
        return Deepworld.MOD_ID;
    }
}
