package com.tmelement;

import com.tmelement.items.ItemIronParticle;
import com.tmelement.primalconditions.BlockBreakHandler;
import com.tmelement.primalconditions.GravelDropsHandler;
import com.tmelement.primalconditions.LeafDropsHandler;
import com.tmelement.primaltools.ItemPrimalAxe;
import com.tmelement.primaltools.ItemPrimalPickaxe;
import com.tmelement.primaltools.ItemPrimalSword;
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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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
    public static Item ironParticle;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        CustomAspectNubes.init();
        CustomAspectScarabaeus.init();
        CustomAspectsSolstitium.init();
        customPrimalAxe = new ItemPrimalAxe(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("customPrimalAxe")
                .setTextureName("yourmodid:custom_primal_axe")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(customPrimalAxe, "customWoodenAxe");
        customPrimalPickaxe = new ItemPrimalPickaxe(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("customWoodenPickaxe")
                .setTextureName("yourmodid:custom_wooden_pickaxe")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(customPrimalPickaxe, "customWoodenPickaxe");
        customPrimalSword = new ItemPrimalSword(Item.ToolMaterial.WOOD)
                .setUnlocalizedName("customPrimalSword")
                .setTextureName("tmelements:custom_primal_sword")
                .setCreativeTab(TMElementCore.tab);
        GameRegistry.registerItem(customPrimalSword, "customPrimalSword");
        ironParticle = new ItemIronParticle();
        GameRegistry.registerItem(ironParticle, "ironParticle");
        MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
        MinecraftForge.EVENT_BUS.register(new LeafDropsHandler());
        MinecraftForge.EVENT_BUS.register(new GravelDropsHandler());
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


