package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Deepworld.MOD_ID)
@Mod.EventBusSubscriber()
public class Deepworld {

    // Mod info
    public static final String MOD_ID = "deepworld";
    public static final String MOD_NAME = "The Deep World";
    public static final String LOG_PREFIX = MOD_NAME;

    public static IEventBus MOD_EVENT_BUS;
    public static ItemGroup itemGroup = new ItemGroupDeepworld();

    public static Logger logger = LogManager.getLogger(LOG_PREFIX);

    public Deepworld() {
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        registerCommonEventsHandler();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> Deepworld::registerClientEventsHandler);
    }

    // Register common events handlers
    public static void registerCommonEventsHandler() {
        MOD_EVENT_BUS.register(DeepworldTileEntityRegister.class);

        MOD_EVENT_BUS.register(DeepworldBlockRegister.class);
        MOD_EVENT_BUS.register(DeepworldItemRegister.class);
    }

    // Register client events handlers
    public static void registerClientEventsHandler() {

    }
}
