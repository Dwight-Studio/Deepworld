package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class DeepworldClient extends Deepworld {
}
