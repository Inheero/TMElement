package com.tmelement.items;

import com.tmelement.TMElementCore;
import net.minecraft.item.Item;

public class ItemAethers extends Item {
    public ItemAethers() {
        setUnlocalizedName("walcirya");
        setTextureName(TMElementCore.MODID +":" +"walcirya");
        setCreativeTab(TMElementCore.tab);
        setMaxStackSize(16);
    }
}