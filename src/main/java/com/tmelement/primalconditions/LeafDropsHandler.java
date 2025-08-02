package com.tmelement.primalconditions;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Iterator;
import java.util.Random;

public class LeafDropsHandler {

    private static final float STICK_DROP_CHANCE = 0.4f; // 40% шанс

    @SubscribeEvent
    public void onLeafHarvest(BlockEvent.HarvestDropsEvent event) {
        // Проверяем, что это листва
        if (event.block == Blocks.leaves || event.block == Blocks.leaves2) {
            World world = event.world;
            Random rand = world.rand;

            // Если выпадает палка (40% шанс)
            if (rand.nextFloat() < STICK_DROP_CHANCE) {
                // Удаляем все саженцы из дропа
                Iterator<ItemStack> iterator = event.drops.iterator();
                while (iterator.hasNext()) {
                    ItemStack stack = iterator.next();
                    if (stack.getItem() == Item.getItemFromBlock(Blocks.sapling)) {
                        iterator.remove();
                    }
                }

                // Добавляем 1 палку
                event.drops.add(new ItemStack(Items.stick, 1));
            }
        }
    }
}
