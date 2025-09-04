package com.tmelement.items;

import com.tmelement.TMElementCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class ItemIngot extends Item {

    public static final String[] ITEMS = new String[] {
            "pirotiumingot",
            "petrotiumingot",
            "creotiumingot",
            "aerotiumingot",
            "filledenderium",
            "enchantmifril",
            "originalmetal",
            "powersinal",
            "thermoinvasivecore",
            "primordialmana",
            "thorium",
            "neptunium",
            "californium",
            "berkelium",
            "einsteinium"
    };

    @SideOnly(Side.CLIENT)
    IIcon[] icons;

    public ItemIngot() {
        this.setHasSubtypes(true);
        this.setUnlocalizedName("ingot");
    }


    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        icons = new IIcon[ITEMS.length];
        for (int x = 0; x < ITEMS.length; x++) {
            icons[x] = ir.registerIcon(TMElementCore.MODID + ":ingot/" + ITEMS[x]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icons[meta % icons.length];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, ITEMS.length - 1);
        return super.getUnlocalizedName() + "." + ITEMS[i];
    }

    @SuppressWarnings({ "unchecked"})
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j < icons.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }

}

