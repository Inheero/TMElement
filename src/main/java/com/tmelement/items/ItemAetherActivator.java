package com.tmelement.items;

import com.tmelement.TMElementCore;
import net.minecraft.item.Item;

public class ItemAetherActivator  extends Item {
    public  ItemAetherActivator() {
        setUnlocalizedName("aetherActivator");
        setTextureName(TMElementCore.MODID +":" +"aetheractivator");
        setCreativeTab(TMElementCore.tab);
        setMaxStackSize(16);
    }
}
