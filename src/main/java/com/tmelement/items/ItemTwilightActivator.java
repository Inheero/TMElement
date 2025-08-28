package com.tmelement.items;

import com.tmelement.TMElementCore;
import net.minecraft.item.Item;

public class ItemTwilightActivator  extends Item {
    public ItemTwilightActivator() {
        setUnlocalizedName("twilightActivator");
        setTextureName(TMElementCore.MODID +":" +"twilightactivator");
        setCreativeTab(TMElementCore.tab);
        setMaxStackSize(16);
    }
}

