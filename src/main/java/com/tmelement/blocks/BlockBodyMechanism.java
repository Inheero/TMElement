package com.tmelement.blocks;

import com.tmelement.TMElementCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockBodyMechanism extends Block {
    public static String[] types = new String[]{
    };

    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public BlockBodyMechanism() {
        super(Material.rock);
        setBlockName("body_mechanism");
        setHardness(5F);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list){
        for(int x = 0;x < types.length;x++)
            list.add(new ItemStack(item, 1, x));
    }

    @Override
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.icons = new IIcon[types.length];
        for (int a = 0; a < types.length; a++) {
            this.icons[a] = par1IconRegister.registerIcon(TMElementCore.MODID + ":body_mechanism/" + types[a]);
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return this.icons[metadata % types.length];
    }

    public int damageDropped(int metadata)
    {
        return metadata;
    }
}
