package com.tmelement.proxy;

import com.tmelement.biomes.BambooRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    public static int bambooRenderID;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.registerRenderers();
    }

    @Override
    public void registerRenderers() {
        // Регистрируем кастомный рендер
        bambooRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BambooRenderer());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    public static void registerItemRenderer(Item item, IItemRenderer render) {
        MinecraftForgeClient.registerItemRenderer(item, render);
    }

    public static void registerItemRenderer(Block block, IItemRenderer render) {
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), render);
    }

    public static void registerTileRenderer(Class<? extends TileEntity> tile, TileEntitySpecialRenderer render) {
        ClientRegistry.bindTileEntitySpecialRenderer(tile, render);
    }
}