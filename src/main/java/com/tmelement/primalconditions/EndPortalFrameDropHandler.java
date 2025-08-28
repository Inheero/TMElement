package com.tmelement.primalconditions;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class EndPortalFrameDropHandler {
    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.block == Blocks.end_portal_frame) {
            List<ItemStack> drops = event.drops;

            // Добавляем сам блок в дроп
            drops.add(new ItemStack(Blocks.end_portal_frame, 1));
        }
    }
}
