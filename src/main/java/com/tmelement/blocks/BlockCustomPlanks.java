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

public class BlockCustomPlanks extends Block {
    // Только 4 кастомных типа досок
    public static final String[] types = new String[] {
            "custom1",
            "custom2",
            "custom3",
            "custom4"
    };

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockCustomPlanks() {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName("customPlanks");
        setCreativeTab(TMElementCore.tab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[types.length];

        for (int i = 0; i < types.length; i++) {
            this.icons[i] = iconRegister.registerIcon(TMElementCore.MODID + ":planks_" + types[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return this.icons[meta % types.length]; // Автоматическая защита от выхода за границы
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < types.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

