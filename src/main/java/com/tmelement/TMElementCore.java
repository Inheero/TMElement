package com.tmelement;

import com.tmelement.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}


