package com.tmelement.blocks;

import com.tmelement.TMElementCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockScorchedLifstone extends Block {
    public BlockScorchedLifstone() {
        super(Material.rock);
        setBlockName("BlockScorchedLifstone");
        setBlockTextureName(TMElementCore.MODID+ ":BlockScorchedLifstone");
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeStone);
        setCreativeTab(TMElementCore.tab);
    }
}

