package com.tmelement.primalconditions;

import com.tmelement.TMElementCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GravelDropsHandler {

    private static final float IRON_PARTICLE_CHANCE = 0.25f; // 25% шанс

    @SubscribeEvent
    public void onGravelHarvest(BlockEvent.HarvestDropsEvent event) {
        if (event.block == Blocks.gravel && isShovel(event.harvester)) {
            Random rand = event.world.rand;

            if (rand.nextFloat() < IRON_PARTICLE_CHANCE) {
                removeFlints(event.drops);
                event.drops.add(new ItemStack(TMElementCore.ironParticle, getDropCount(rand)));
            }
        }
    }

    private boolean isShovel(EntityPlayer player) {
        return player != null &&
                player.getHeldItem() != null &&
                player.getHeldItem().getItem().getToolClasses(player.getHeldItem()).contains("shovel");
    }

    private void removeFlints(List<ItemStack> drops) {
        Iterator<ItemStack> it = drops.iterator();
        while (it.hasNext()) {
            if (it.next().getItem() == Items.flint) {
                it.remove();
            }
        }
    }

    private int getDropCount(Random rand) {
        return 1 + rand.nextInt(2); // 1-3 частицы
    }
}
