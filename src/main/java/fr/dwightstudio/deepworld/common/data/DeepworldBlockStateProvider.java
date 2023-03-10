package fr.dwightstudio.deepworld.common.data;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DeepworldBlockStateProvider extends BlockStateProvider {

    ExistingFileHelper fileHelper;

    public DeepworldBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Deepworld.MOD_ID, exFileHelper);
        this.fileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
