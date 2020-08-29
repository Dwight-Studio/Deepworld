package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Deepworld.MOD_ID)
@Mod.EventBusSubscriber()
public class Deepworld {

    public static final String MOD_ID = "deepworld";
    public static final String MOD_NAME = "The Deep World";
    public static final String LOG_PREFIX = MOD_NAME;
    public static CreativeTabs creativeTab = new CreativeTabDeepworld();

    public static Logger logger = LogManager.getLogger(LOG_PREFIX);

    // This is the instance of your mod as created by Forge. It will never be null.
    @Mod.Instance(MOD_ID)
    public static Deepworld INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        registerTileEntity();
    }

    // This is the second initialization event. Register custom recipes
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Joining the stage...");
    }

    // This is the final initialization event. Register actions from other mods here
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    // Register items & blocks
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        // Register blocks and tile entities
        logger.debug("Registering blocks...");
        DeepworldBlocks.registerBlocks(event.getRegistry());
        logger.debug("Done!");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        // Register items and itemBlocks
        logger.debug("Registering items...");
        DeepworldItems.registerItems(event.getRegistry());
        DeepworldItems.registerItemBlocks(event.getRegistry());
        // Integrate certain OreDictionary recipes
        registerOreDict();
        logger.debug("Done!");
    }

    public static void registerTileEntity() {
        logger.debug("Registering tileEntities...");
        GameRegistry.registerTileEntity(TileEntityWoodenFrame.class, new ResourceLocation(Deepworld.MOD_ID, "wooden_frame"));
        logger.debug("Done!");

    }

    private static void registerOreDict() {

        OreDictionary.registerOre("frameWood", DeepworldItems.WOODEN_FRAME);
        OreDictionary.registerOre("frameIron", DeepworldItems.IRON_FRAME);
        OreDictionary.registerOre("frameSteel", DeepworldItems.STEEL_FRAME);
        OreDictionary.registerOre("frameObsidianSteel", DeepworldItems.OBSIDIAN_INFUSED_STEEL_FRAME);

    }
}
