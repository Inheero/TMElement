package com.tmelement;

import com.tmelement.botaniaintegration.ManaGiverItem;
import com.tmelement.fluids.ItemWoodBucket;
import com.tmelement.items.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class ModItems {
    public static final Item TM_WOOD = new ItemWoodBucket(Blocks.water);
    public static final Item MA_MANAGIVER= new ManaGiverItem();
    public static final Item MA_INGOTS= new ItemAethers();
    public static final Item TM_SEEDS = new ItemSeeds();
    public static final Item TM_AETHERACTIVATOR = new ItemAetherActivator();
    public static final Item TM_TWILIGHTACTIVATOR = new ItemTwilightActivator();
    public static final Item TM_SOAKED = new ItemSoakedIngots();
    public static final Item TM_SPLAV = new ItemSplav();
    public static final Item TM_SPLAVINGOT = new ItemSplavingot();
    public static final Item TM_SOLARCORE = new ItemSolarcore();
    public static final Item TM_INGOT = new ItemIngot();
    public static final Item TM_TECHNOMAGIC = new ItemTechnomagic();

    public static void registerItems() {
        registerItem(TM_WOOD);
        registerItem(MA_MANAGIVER);
        registerItem( MA_INGOTS);
        MinecraftForge.EVENT_BUS.register(MA_MANAGIVER);
        registerItem(TM_SEEDS);
        registerItem(TM_AETHERACTIVATOR);
        registerItem(TM_TWILIGHTACTIVATOR);
        registerItem(TM_SOAKED);
        registerItem(TM_TECHNOMAGIC);
        registerItem(TM_SPLAVINGOT);
        registerItem(TM_SPLAV);
        registerItem(TM_SOLARCORE);
        registerItem(TM_INGOT);
    }

    public static void registerItem(Item item) {
        item.setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
}
