package com.tmelement.blocks;

import com.tmelement.TMElementCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockScorchedLifeWood extends Block {
    public BlockScorchedLifeWood() {
        super(Material.wood);
        setBlockName("BlockScorchedLifeWood");
        setBlockTextureName(TMElementCore.MODID+ ":BlockScorchedLifeWood");
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeStone);
        setCreativeTab(TMElementCore.tab);
    }
}