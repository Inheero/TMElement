package com.tmelement.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCustomPlanks extends ItemBlock {
    public ItemCustomPlanks(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        if (meta < 0 || meta >= BlockCustomPlanks.types.length) {
            meta = 0;
        }
        return super.getUnlocalizedName() + "." + BlockCustomPlanks.types[meta];
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}