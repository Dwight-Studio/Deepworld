package fr.dwightstudio.deepworld.common;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Deepworld.MOD_ID)
@Mod.EventBusSubscriber()
public class Deepworld {

    // Mod info
    public static final String MOD_ID = "deepworld";
    public static final String MOD_NAME = "The Deep World";
    public static final String LOG_PREFIX = MOD_NAME;
    public static final CreativeModeTab MOD_TAB = new DeepworldCreativeTab();

    public static IEventBus MOD_EVENT_BUS;

    public static Logger logger = LogManager.getLogger(LOG_PREFIX);

    // Registering
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item>  ITEMS  = DeferredRegister.create(ForgeRegistries.ITEMS,  MOD_ID);

    public Deepworld() {
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        // Registries
        Deepworld.registerAssets();

        BLOCKS.register(MOD_EVENT_BUS);
        ITEMS.register(MOD_EVENT_BUS);

        /*DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> Deepworld::registerClientEventsHandler);*/
    }

    public static void registerAssets() {
        new DeepworldBlocks();
        new DeepworldItems();
    }
/*
    // Register client events handlers
    public static void registerClientEventsHandler() {

    }*/
}
