package com.tmelement.blocks;

import com.tmelement.TMElementCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockAlfheimSlab extends BlockSlab {

    public BlockAlfheimSlab(boolean isDouble) {
        super(isDouble, Material.rock);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockName(isDouble ? "block_alfheim_slab_double" : "block_alfheim_slab");
        this.setCreativeTab(TMElementCore.tab);
        this.setBlockTextureName(TMElementCore.MODID+":block_alfheim_slab");
        this.setBlockTextureName(TMElementCore.MODID+":block_alfheim_slab_double");
    }

    @Override
    public String func_150002_b(int meta) {
        return "custom_slab";
    }
}