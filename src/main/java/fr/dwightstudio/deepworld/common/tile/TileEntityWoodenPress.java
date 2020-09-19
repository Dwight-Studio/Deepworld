package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWoodenPress extends TileEntity implements ITickableTileEntity {


    public TileEntityWoodenPress() {
        super(DeepworldTileEntities.WOODEN_PRESS);
    }

    @Override
    public void tick() {

    }
}
