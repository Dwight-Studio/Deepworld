package fr.dwightstudio.deepworld.mixin;

import fr.dwightstudio.deepworld.ExampleModDeepworld;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ExampleMixinDeepworld {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        ExampleModDeepworld.LOGGER.info("This line is printed by an example mod mixin!");
    }
}
