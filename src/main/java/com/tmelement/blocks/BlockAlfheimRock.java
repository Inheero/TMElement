package com.tmelement.blocks;

import com.tmelement.TMElementCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAlfheimRock extends Block {
    public BlockAlfheimRock() {
        super(Material.rock);
        setBlockName("alfheimRock");
        setBlockTextureName(TMElementCore.MODID+ ":alfheimRock");
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeStone);
        setCreativeTab(TMElementCore.tab);
    }
}