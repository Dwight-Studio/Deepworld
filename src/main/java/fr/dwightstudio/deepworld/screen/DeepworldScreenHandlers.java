package fr.dwightstudio.deepworld.screen;

import fr.dwightstudio.deepworld.Deepworld;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class DeepworldScreenHandlers{

    public static ScreenHandlerType<WoodenLatheScreenHandler> WOODEN_LATHE_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(WoodenLatheScreenHandler::new);

    public static void registerAllScreenHandlers(){
        WOODEN_LATHE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Deepworld.MOD_ID, "wooden_lathe_screen_handler"), WOODEN_LATHE_SCREEN_HANDLER);
    }

}
