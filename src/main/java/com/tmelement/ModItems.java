package com.tmelement;

import com.tmelement.botaniaintegration.ManaGiverItem;
import com.tmelement.fluids.ItemWoodBucket;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class ModItems {
    public static final Item TM_WOOD = new ItemWoodBucket(Blocks.water);
    public static final Item MA_MANAGIVER= new ManaGiverItem();

    public static void registerItems() {
        registerItem(TM_WOOD);
        registerItem(MA_MANAGIVER);
        MinecraftForge.EVENT_BUS.register(MA_MANAGIVER);
    }

    public static void registerItem(Item item) {
        item.setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
}
