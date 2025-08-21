package com.tmelement.items;

import com.tmelement.TMElementCore;
import net.minecraft.item.Item;

public class ItemRope extends Item {
    public ItemRope() {
        setUnlocalizedName("defRope");
        setTextureName(TMElementCore.MODID +":" +"def_rope");
        setCreativeTab(TMElementCore.tab);
        setMaxStackSize(64);
    }
}