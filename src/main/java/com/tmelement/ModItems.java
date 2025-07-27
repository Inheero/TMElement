package com.tmelement;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {

    public static void registerItems() {

    }

    public static void registerItem(Item item) {
        item.setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
}
