package com.tmelement;

import com.tmelement.biomes.BiomeBambooForest;
import com.tmelement.blocks.BlockBamboo;
import com.tmelement.blocks.ItemBambooBlock;
import com.tmelement.items.ItemIronParticle;
import com.tmelement.primalconditions.BlockBreakHandler;
import com.tmelement.primalconditions.GravelDropsHandler;
import com.tmelement.primalconditions.LeafDropsHandler;
import com.tmelement.primaltools.*;
import com.tmelement.proxy.CommonProxy;
import com.tmelement.thaumcraftintegration.aspects.CustomAspectNubes;
import com.tmelement.thaumcraftintegration.aspects.CustomAspectScarabaeus;
import com.tmelement.thaumcraftintegration.aspects.CustomAspectsSolstitium;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = TMElementCore.MODID, version = TMElementCore.VERSION, name = TMElementCore.MODNAME)
public class TMElementCore {
    public static final String MODID = "tmelements";
    public static final String VERSION = "@VERSION@";
    public static final String MODNAME = "TM Elements";

    @Mod.Instance(MODID)
    public static TMElementCore instance;

    public static CreativeTabs tab = new CreativeTabs("tm_element_tab") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.sponge);
        }
    };

    @SidedProxy(clientSide = "com.tmelement.proxy.ClientProxy", serverSide = "com.tmelement.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Item customPrimalAxe;
    public static Item customPrimalPickaxe;
    public static Item customPrimalSword;
    public static Item primalShovel;
    public static Item primalHoe;
    public static Item ironParticle;
    public static Block bamboo;
    public static BiomeGenBase bambooForest;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        CustomAspectNubes.init();
        CustomAspectScarabaeus.init();
        CustomAspectsSolstitium.init();
        customPrimalAxe = new ItemPrimalAxe(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("customPrimalAxe")
                .setTextureName(MODID +":custom_primal_axe")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(customPrimalAxe, "customWoodenAxe");
        customPrimalPickaxe = new ItemPrimalPickaxe(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("customWoodenPickaxe")
                .setTextureName(MODID +":custom_wooden_pickaxe")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(customPrimalPickaxe, "customWoodenPickaxe");
        customPrimalSword = new ItemPrimalSword(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("customPrimalSword")
                .setTextureName(MODID +":custom_primal_sword")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(customPrimalSword, "customPrimalSword");
        primalShovel = new ItemPrimalShovel(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("primalShovel")
                .setTextureName(MODID +":primal_shovel")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(primalShovel, "primalShovel");
        primalHoe = new ItemPrimalHoe(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("primalHoe")
                .setTextureName(MODID +":primal_hoe")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(primalHoe, "primalHoe");
        ironParticle = new ItemIronParticle();
        GameRegistry.registerItem(ironParticle, "ironParticle");
        MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
        MinecraftForge.EVENT_BUS.register(new LeafDropsHandler());
        MinecraftForge.EVENT_BUS.register(new GravelDropsHandler());
        bamboo = new BlockBamboo();
        GameRegistry.registerBlock(bamboo, ItemBambooBlock.class, "bamboo");
        bambooForest = new BiomeBambooForest(150);
        BiomeManager.addSpawnBiome(bambooForest);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
    //sosal?  git config --global user.email "you@example.com"
}


