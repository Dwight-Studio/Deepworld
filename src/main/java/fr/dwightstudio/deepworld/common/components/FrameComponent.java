package fr.dwightstudio.deepworld.common.components;

import net.minecraft.world.item.Item;

public interface FrameComponent {

    public String getName();

    public Item getItem();

    public int getID();

    public ComponentClass getComponentClass();
}
