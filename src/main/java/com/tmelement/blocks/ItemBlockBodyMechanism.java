package com.tmelement.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBodyMechanism extends ItemBlock {

    public ItemBlockBodyMechanism(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "tile.body_mechanism." + BlockBodyMechanism.types[itemStack.getItemDamage()];
    }


}
