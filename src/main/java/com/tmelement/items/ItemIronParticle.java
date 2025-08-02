package com.tmelement.items;

import com.tmelement.TMElementCore;
import net.minecraft.item.Item;

public class ItemIronParticle extends Item {
    public ItemIronParticle() {
        setUnlocalizedName("ironParticle");
        setTextureName("tmelements:iron_particle");
        setCreativeTab(TMElementCore.tab);
        setMaxStackSize(16);
    }
}
