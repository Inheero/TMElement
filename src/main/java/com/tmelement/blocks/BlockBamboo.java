package com.tmelement.blocks;

import com.tmelement.TMElementCore;
import com.tmelement.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

public class BlockBamboo extends Block implements IPlantable {
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockBamboo() {
        super(Material.plants);
        setBlockName("bamboo");
        setHardness(0.5F);
        setStepSound(soundTypeGrass);
        setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
        setTickRandomly(true);
        setCreativeTab(TMElementCore.tab);
    }

    // IPlantable implementation
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }

    // Growth logic
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote && world.isAirBlock(x, y + 1, z)) {
            int height = getBambooHeight(world, x, y, z);
            if (height < 15 && rand.nextInt(4) == 0) {
                world.setBlock(x, y + 1, z, this);
                world.playAuxSFX(2005, x, y + 1, z, 0);
            }
        }
    }

    private int getBambooHeight(World world, int x, int y, int z) {
        int height = 1;
        while (world.getBlock(x, y - height, z) == this) {
            height++;
        }
        return height;
    }

    // Block physics
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block below = world.getBlock(x, y - 1, z);
        return below.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) || below == this;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!canBlockStay(world, x, y, z)) {
            breakTallPlant(world, x, y, z, true);
        }
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        return canPlaceBlockAt(world, x, y, z);
    }

    // Breaking logic
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        breakTallPlant(world, x, y, z, true);
    }

    private void breakTallPlant(World world, int x, int y, int z, boolean drop) {
        for (int i = y + 1; i < world.getHeight(); i++) {
            if (world.getBlock(x, i, z) == this) {
                if (drop) {
                    world.func_147480_a(x, i, z, true);
                } else {
                    world.setBlockToAir(x, i, z);
                }
            } else {
                break;
            }
        }
    }

    // Drops
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int baseQuantity = quantityDropped(world.rand);
        int bonus = world.rand.nextInt(fortune + 1);
        drops.add(new ItemStack(getItemDropped(metadata, world.rand, fortune), baseQuantity + bonus));
        return drops;
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(this); // Будет дропать сам блок
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    // Rendering
    @Override
    public int getRenderType() {
        return ClientProxy.bambooRenderID;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icon = reg.registerIcon("tmelements:bamboo");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return icon;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}