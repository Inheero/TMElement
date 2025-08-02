package com.tmelement.primalconditions;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.world.BlockEvent;

public class BlockBreakHandler {
    @SubscribeEvent
    public void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {
        if (event.block.getMaterial() == Material.wood) {
            if (event.harvester != null && event.harvester.getHeldItem() == null) {
                event.drops.clear();

                event.world.playAuxSFX(2001, event.x, event.y, event.z,
                        Block.getIdFromBlock(event.block) + (event.blockMetadata << 12));

                if (event.harvester instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) event.harvester;
                    if (!player.capabilities.isCreativeMode) {
                        player.addChatMessage(new ChatComponentText(
                                StatCollector.translateToLocal("message.tmelements.cant_break_wood")
                        ));
                    }
                }
            }
        }
    }
}
