package com.tmelement.items;

import com.tmelement.TMElementCore;
import net.minecraft.item.Item;

public class ItemPeltCow extends Item {
    public ItemPeltCow() {
        setUnlocalizedName("peltCow");
        setTextureName(TMElementCore.MODID +":" +"pelt_cow");
        setCreativeTab(TMElementCore.tab);
        setMaxStackSize(64);
    }
}