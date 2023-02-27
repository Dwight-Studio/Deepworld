package fr.dwightstudio.deepworld.mixin;

import fr.dwightstudio.deepworld.Deepworld;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinDeepworld {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        Deepworld.LOGGER.info("Ceci est un test!");
    }
}
