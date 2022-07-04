package fr.dwightstudio.deepworld.client.sound;

import fr.dwightstudio.deepworld.common.tile.ITileEntityWoodenMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class TickableSoundWoodenMachine implements TickableSoundInstance {

    private double x, y, z;
    private float volume;
    private boolean repeat;
    private int repeatDelay;

    public TickableSoundWoodenMachine(BlockPos blockPos) {
        this.x = blockPos.getX() + 0.5F;
        this.y = blockPos.getY() + 0.5F;
        this.z = blockPos.getZ() + 0.5F;
        this.volume = 0.0F;

        this.repeat = true;
        this.repeatDelay = 0;
    }

    @Override
    public boolean isStopped() {
        return false;
    }

    @Override
    public void tick() {
        this.volume = getTileVolume();
    }

    private float getTileVolume() {
        assert Minecraft.getInstance().level != null;
        BlockEntity tile = Minecraft.getInstance().level.getBlockEntity(new BlockPos(getX(), getY(), getZ()));

        if (tile instanceof ITileEntityWoodenMachine woodenMachine) {
            return woodenMachine.getVolume();
        } else {
            return 0.0F;
        }
    }

    @Override
    public ResourceLocation getLocation() {
        return null;
    }

    @Nullable
    @Override
    public WeighedSoundEvents resolve(SoundManager p_119841_) {
        return null;
    }

    @Override
    public Sound getSound() {
        return null;
    }

    @Override
    public SoundSource getSource() {
        return null;
    }

    @Override
    public boolean isLooping() {
        return repeat;
    }

    @Override
    public boolean isRelative() {
        return false;
    }

    @Override
    public int getDelay() {
        return repeatDelay;
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    public float getPitch() {
        return 0;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public Attenuation getAttenuation() {
        return null;
    }
}
