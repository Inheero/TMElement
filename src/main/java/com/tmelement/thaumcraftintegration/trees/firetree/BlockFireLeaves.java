package com.tmelement.thaumcraftintegration.trees.firetree;

import com.tmelement.TMElementCore;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockFireLeaves extends BlockLeavesBase {
    public BlockFireLeaves() {
        super(Material.leaves, false);
        this.setHardness(0.2F);
        this.setStepSound(soundTypeGrass);
        this.setBlockTextureName(TMElementCore.MODID +":custom_leaves");
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(TMElementCore.fireSapling); // Выпадение саженца
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0; // Шанс выпадения саженца
    }

    @Override
    public int getRenderColor(int metadata) {
        return 0x00FF00; // Зеленый цвет листвы
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
}