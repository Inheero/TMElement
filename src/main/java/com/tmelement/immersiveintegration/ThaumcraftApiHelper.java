package com.tmelement.immersiveintegration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ThaumcraftApiHelper {
    public static boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit) {
        if (wand == null || !(wand.getItem() instanceof ItemWandCasting)) {
            return false;
        }
        ItemWandCasting wandItem = (ItemWandCasting) wand.getItem();
        return wandItem.consumeAllVis(wand, player, cost, doit, false);
    }
}
