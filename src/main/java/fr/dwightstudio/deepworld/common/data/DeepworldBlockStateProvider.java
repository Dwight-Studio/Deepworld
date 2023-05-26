/*
 *       ____           _       __    __     _____ __            ___
 *      / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *     / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *    / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 *   /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                    /____/
 *   Copyright (c) 2022-2023 Dwight Studio's Team <support@dwight-studio.fr>
 *
 *   This Source Code From is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 *
 */

package fr.dwightstudio.deepworld.common.data;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blocks.frames.FrameBlock;
import fr.dwightstudio.deepworld.common.blocks.machines.WoodenMachineBlock;
import fr.dwightstudio.deepworld.common.blocks.tanks.TankBlock;
import fr.dwightstudio.deepworld.common.components.FrameComponent;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Collectors;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class DeepworldBlockStateProvider extends BlockStateProvider {

    ExistingFileHelper existingFileHelper;
    public DeepworldBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
        super(packOutput, Deepworld.MOD_ID, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Deepworld.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    @Override
    protected void registerStatesAndModels() {

        registerFrame(DeepworldBlocks.WOODEN_FRAME);
        registerWoodenMachine(DeepworldBlocks.WOODEN_PRESS);
        registerWoodenMachine(DeepworldBlocks.WOODEN_LATHE);
        registerWoodenMachine(DeepworldBlocks.WOODEN_GEAR_SHAPER);

        registerCubeAll(DeepworldBlocks.STEEL_BLOCK);
        registerCubeAll(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);

        registerTank(DeepworldBlocks.IRON_TANK);
        registerPipe(DeepworldBlocks.PIPE);

        for (Block block : getKnownBlocks()) {
            if (!registeredBlocks.containsKey(block)) {
                throw new IllegalStateException(String.format(Locale.ROOT, "Missing blockstate for '%s'", ForgeRegistries.BLOCKS.getKey(block)));
            }
        }
    }

    private void registerCubeAll(RegistryObject<Block> block) {
        ModelFile model = models().cubeAll(block.getId().getPath(), Deepworld.loc(BLOCK_FOLDER + "/" + block.getId().getPath()));
        getVariantBuilder(block.get())
                .partialState()
                .modelForState()
                .modelFile(model)
                .addModel();
    }

    private void registerWoodenMachine(RegistryObject<Block> machine) {
        String name = machine.getId().getPath();

        ModelFile idle = models().withExistingParent(name,Deepworld.loc("wooden_machine"))
                .texture("wooden_case_panel", Deepworld.loc(BLOCK_FOLDER + "/wooden_frame"))
                .texture("wooden_front", Deepworld.loc(BLOCK_FOLDER + "/wooden_front"))
                .texture("wooden_machine", Deepworld.loc(BLOCK_FOLDER + "/" + name));

        ModelFile working = models().withExistingParent(name + "_working", Deepworld.loc("wooden_machine"))
                .texture("wooden_case_panel", Deepworld.loc(BLOCK_FOLDER + "/wooden_frame"))
                .texture("wooden_front", Deepworld.loc(BLOCK_FOLDER + "/wooden_front"))
                .texture("wooden_machine", Deepworld.loc(BLOCK_FOLDER + "/" + name + "_anim"));

        getVariantBuilder(machine.get())
                .forAllStates(state -> {
                    if (state.getValue(WoodenMachineBlock.WORKING)) {
                        return ConfiguredModel.builder()
                                .modelFile(working)
                                .rotationY((int) state.getValue(WoodenMachineBlock.FACING).toYRot())
                                .build();
                    } else {
                        return ConfiguredModel.builder()
                                .modelFile(idle)
                                .rotationY((int) state.getValue(WoodenMachineBlock.FACING).toYRot())
                                .build();
                    }
                });
    }

    private void registerFrame(RegistryObject<Block> frame) {
        String name = frame.getId().getPath();
        FrameBlock frameBlock = (FrameBlock) frame.get();

        ModelFile frameModel = models().withExistingParent(name, Deepworld.loc("frame"))
                .texture("frame", Deepworld.loc(BLOCK_FOLDER + "/" + name));

        HashMap<FrameComponent, ModelFile> allModels = new HashMap<>();

        Arrays.stream(frameBlock.getAllComponents()).forEach(
                frameComponent -> allModels.put(frameComponent, new ModelFile.ExistingModelFile(Deepworld.loc("block/" + frameComponent.getName().toLowerCase()), existingFileHelper))
        );

        ModelFile casePanel = models().withExistingParent(frameBlock.getCoverPath(), Deepworld.loc("block/case_panel"))
                .texture("frame", Deepworld.loc(BLOCK_FOLDER + "/" + frame.getId().getPath()));

        MultiPartBlockStateBuilder builder = getMultipartBuilder(frameBlock);

        builder.part()
                .modelFile(frameModel)
                .addModel()
                .end();

        for (int y = 0; y < 360; y+= 90) {
            for (FrameComponent frameComponent : frameBlock.getAllComponents()) {
                allModels.get(frameComponent).assertExistence();

                builder.part()
                        .modelFile(allModels.get(frameComponent))
                        .rotationY(y)
                        .addModel()
                        .nestedGroup()
                        .condition(FrameBlock.FACING, Direction.fromYRot(y))
                        .condition(frameBlock.getProperty(frameComponent.getComponentClass().getIndex()), frameComponent.getID())
                        //.endNestedGroup()
                        .end();
            }
            for (int i = 1; i < 7; i++) {
                MultiPartBlockStateBuilder.PartBuilder mBuilder = builder.part()
                        .modelFile(casePanel)
                        .rotationX((i > 1 && i < 6) ? 90 : (i == 1 ? 0 : 180))
                        .rotationY(y + ((i > 1 && i < 6) ? (i-3) * 90 : 0))
                        .addModel()
                        .useOr();

                for (int u = i; u < 7; u++) {
                    mBuilder.nestedGroup()
                            .condition(FrameBlock.COVER, u)
                            .condition(FrameBlock.FACING, Direction.fromYRot(y))
                            .end();
                }
            }
        }
    }

    private void registerTank(RegistryObject<Block> tank) {
        String name = tank.getId().getPath();
        TankBlock tankBlock = (TankBlock) tank.get();

        ModelFile tankModel = models().withExistingParent(name, Deepworld.loc("tank"))
                .texture("tank", Deepworld.loc(BLOCK_FOLDER + "/" + name));

        ModelFile tankBottom = models().withExistingParent(name, Deepworld.loc("tank_bottom"))
                .texture("tank", Deepworld.loc(BLOCK_FOLDER + "/" + name));

        ModelFile tankMiddle = models().withExistingParent(name, Deepworld.loc("tank_middle"))
                .texture("tank", Deepworld.loc(BLOCK_FOLDER + "/" + name));

        ModelFile tankTop = models().withExistingParent(name, Deepworld.loc("tank_top"))
                .texture("tank", Deepworld.loc(BLOCK_FOLDER + "/" + name));

        getVariantBuilder(tankBlock).partialState()
                .with(TankBlock.UP, false)
                .with(TankBlock.DOWN, false)
                .modelForState()
                .modelFile(tankModel)
                .addModel()
                .partialState()
                .with(TankBlock.UP, false)
                .with(TankBlock.DOWN, true)
                .modelForState()
                .modelFile(tankTop)
                .addModel()
                .partialState()
                .with(TankBlock.UP, true)
                .with(TankBlock.DOWN, true)
                .modelForState()
                .modelFile(tankMiddle)
                .addModel()
                .partialState()
                .with(TankBlock.UP, true)
                .with(TankBlock.DOWN, false)
                .modelForState()
                .modelFile(tankBottom)
                .addModel();
    }

    private void registerPipe(RegistryObject<Block> pipe) {
        String name = pipe.getId().getPath();
        PipeBlock pipeBlock = (PipeBlock) pipe.get();

        ModelFile model = new ModelFile.ExistingModelFile(Deepworld.loc( "block/pipe"), existingFileHelper);

        getVariantBuilder(pipeBlock)
                .forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
    }
}
