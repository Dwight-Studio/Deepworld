package fr.dwightstudio.deepworld.common.data;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.stream.Collectors;

public class DeepworldBlockModelProvider extends BlockModelProvider {

    public DeepworldBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Deepworld.MOD_ID, existingFileHelper);
    }

    protected @NotNull Iterable<ResourceLocation> getKnownBlocks() {
        return Deepworld.BLOCKS.getEntries().stream().map(RegistryObject::getId).collect(Collectors.toList());
    }

    @Override
    protected void registerModels() {
        registerFrame(DeepworldBlocks.WOODEN_FRAME.getId());
        registerWoodenMachine(DeepworldBlocks.WOODEN_PRESS.getId());
        registerWoodenMachine(DeepworldBlocks.WOODEN_LATHE.getId());
        registerWoodenMachine(DeepworldBlocks.WOODEN_GEAR_SHAPER.getId());

        registerCubeAll(DeepworldBlocks.STEEL_BLOCK.getId());
        registerCubeAll(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK.getId());

        registerTank(DeepworldBlocks.IRON_TANK.getId());

        for (ResourceLocation block : getKnownBlocks()) {
            if (!generatedModels.containsKey(block.withPrefix( BLOCK_FOLDER + "/")) && !existingFileHelper.exists(block.withPrefix( BLOCK_FOLDER + "/"), MODEL)) {
                throw new IllegalStateException(String.format(Locale.ROOT, "Missing block model '%s' for '%s'", block.withPrefix(BLOCK_FOLDER + "/"), block));
            }
        }
    }

    private void registerCubeAll(ResourceLocation block) {
        cubeAll(block.getPath(), modLoc(BLOCK_FOLDER + "/" + block.getPath()));
    }

    private void registerWoodenMachine(ResourceLocation machine) {
        String name = machine.getPath();

        withExistingParent(name,modLoc("wooden_machine"))
                .texture("wooden_case_panel", modLoc(BLOCK_FOLDER + "/wooden_frame"))
                .texture("wooden_front", modLoc(BLOCK_FOLDER + "/wooden_front"))
                .texture("wooden_machine", modLoc(BLOCK_FOLDER + "/" + name));

        withExistingParent(name + "_working", modLoc("wooden_machine"))
                .texture("wooden_case_panel", modLoc(BLOCK_FOLDER + "/wooden_frame"))
                .texture("wooden_front", modLoc(BLOCK_FOLDER + "/wooden_front"))
                .texture("wooden_machine", modLoc(BLOCK_FOLDER + "/" + name + "_anim"));
    }

    private void registerFrame(ResourceLocation frame) {
        String name = frame.getPath();

        withExistingParent(name, modLoc("frame"))
                .texture("frame", modLoc(BLOCK_FOLDER + "/" + name));
    }

    private void registerTank(ResourceLocation tank) {
        String name = tank.getPath();

        withExistingParent(name, modLoc("tank"))
                .texture("tank", modLoc(BLOCK_FOLDER + "/" + name));

        withExistingParent(name, modLoc("tank_bottom"))
                .texture("tank", modLoc(BLOCK_FOLDER + "/" + name));

        withExistingParent(name, modLoc("tank_middle"))
                .texture("tank", modLoc(BLOCK_FOLDER + "/" + name));

        withExistingParent(name, modLoc("tank_top"))
                .texture("tank", modLoc(BLOCK_FOLDER + "/" + name));
    }
}
