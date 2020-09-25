package fr.dwightstudio.deepworld.client.sound;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldSoundEvents;
import fr.dwightstudio.deepworld.common.tile.ITileEntityWoodenMachine;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TickableSoundWoodenMachine extends TickableSound {

    public TickableSoundWoodenMachine(BlockPos blockPos) {
        super(DeepworldSoundEvents.WOODEN_MACHINE, SoundCategory.BLOCKS);

        this.x = blockPos.getX() + 0.5F;
        this.y = blockPos.getY() + 0.5F;
        this.z = blockPos.getZ() + 0.5F;
        this.volume = 0F;

        this.repeat = true;
        this.repeatDelay = 0;
    }

    @Override
    public void tick() {
        this.volume = (this.volume + getTileVolume())/2;
    }

    @Override
    public boolean canBeSilent() {
        return true;
    }

    private float getTileVolume() {
        TileEntity tile = Minecraft.getInstance().world.getTileEntity(new BlockPos(getX(), getY(), getZ()));

        if (tile instanceof ITileEntityWoodenMachine) {
            ITileEntityWoodenMachine woodenMachine = (ITileEntityWoodenMachine) tile;

            return woodenMachine.getVolume();
        } else {
            this.donePlaying = true;
            return 0.0F;
        }
    }
}
