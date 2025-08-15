package com.tmelement.thaumcraftintegration.trees.watertree;

import com.tmelement.TMElementCore;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockWaterSapling extends BlockSapling {
    private IIcon[] icons;

    public BlockWaterSapling() {
        this.setBlockName("customSaplingwater");
        this.setBlockTextureName(TMElementCore.MODID +":custom_sapling_water");
        this.setCreativeTab(TMElementCore.tab);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, rand);

            if (world.getBlockLightValue(x, y + 1, z) >= 9 && rand.nextInt(7) == 0) {
                this.func_149879_c(world, x, y, z, rand);
            }
        }
    }

    public void func_149879_c(World world, int x, int y, int z, Random rand) {
        if (world.getBlock(x, y, z) == this) {
            generateTree(world, x, y, z, rand);
        }
    }

    public void generateTree(World world, int x, int y, int z, Random rand) {
        world.setBlockToAir(x, y, z);

        // Создаем генератор дерева с параметрами как в Thaumcraft
        // (minHeight = 7, randomHeight = 5 - итого деревья 7-12 блоков высотой)
        WorldGenWaterTree treeGen = new WorldGenWaterTree(true, 7, 5);

        if (!treeGen.generate(world, rand, x, y, z)) {
            // Если не получилось сгенерировать - возвращаем саженец
            world.setBlock(x, y, z, this);
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[1];
        this.icons[0] = reg.registerIcon(this.getTextureName());
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.icons[0];
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
    }
}