package com.tmelement.immersiveintegration;

import blusunrize.immersiveengineering.api.MultiblockHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

import java.util.HashMap;
import java.util.Map;

public class WandMultiblockActivator {

    private static final Map<String, AspectList> MULTIBLOCK_COSTS = new HashMap<String, AspectList>();
    static {
        MULTIBLOCK_COSTS.put("IE:CokeOven",       new AspectList().add(Aspect.FIRE, 10).add(Aspect.ORDER, 10).add(Aspect.EARTH, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10).add(Aspect.ENTROPY, 10));
        MULTIBLOCK_COSTS.put("IE:BlastFurnace",   new AspectList().add(Aspect.FIRE, 10).add(Aspect.ORDER, 10).add(Aspect.EARTH, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10).add(Aspect.ENTROPY, 10));
        MULTIBLOCK_COSTS.put("IE:Crusher",        new AspectList().add(Aspect.FIRE, 40).add(Aspect.ORDER, 40).add(Aspect.EARTH, 40).add(Aspect.WATER, 40).add(Aspect.AIR, 40).add(Aspect.ENTROPY, 40));
        MULTIBLOCK_COSTS.put("IE:ArcFurnace",     new AspectList().add(Aspect.FIRE, 80).add(Aspect.ORDER, 80).add(Aspect.EARTH, 80).add(Aspect.WATER, 80).add(Aspect.AIR, 80).add(Aspect.ENTROPY, 80));
        MULTIBLOCK_COSTS.put("IE:Excavator",      new AspectList().add(Aspect.FIRE, 40).add(Aspect.ORDER, 40).add(Aspect.EARTH, 40).add(Aspect.WATER, 40).add(Aspect.AIR, 40).add(Aspect.ENTROPY, 40));
        MULTIBLOCK_COSTS.put("IE:BucketWheel",    new AspectList().add(Aspect.FIRE, 40).add(Aspect.ORDER, 40).add(Aspect.EARTH, 40).add(Aspect.WATER, 40).add(Aspect.AIR, 40).add(Aspect.ENTROPY, 40));
        MULTIBLOCK_COSTS.put("IE:Silo",           new AspectList().add(Aspect.FIRE, 60).add(Aspect.ORDER, 60).add(Aspect.EARTH, 60).add(Aspect.WATER, 60).add(Aspect.AIR, 60).add(Aspect.ENTROPY, 60));
        MULTIBLOCK_COSTS.put("IE:DieselGenerator",new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENERGY, 50).add(Aspect.ORDER, 50));

    }

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new WandMultiblockActivator());
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack held = player.getHeldItem();
        if (held == null || !(held.getItem() instanceof ItemWandCasting)) return;

        if (player.worldObj.isRemote) return;

        ItemWandCasting wand = (ItemWandCasting) held.getItem();

        for (MultiblockHandler.IMultiblock mb : MultiblockHandler.getMultiblocks()) {

            if (!mb.isBlockTrigger(event.world.getBlock(event.x, event.y, event.z),
                    event.world.getBlockMetadata(event.x, event.y, event.z))) continue;

            AspectList cost = MULTIBLOCK_COSTS.get(mb.getUniqueName());
            if (cost == null) {
                player.addChatMessage(new ChatComponentText("§eДля " + mb.getUniqueName() + " цена не задана."));
                event.setCanceled(true);
                return;
            }

            boolean hasVis = wand.consumeAllVis(held, player, cost, /*doit=*/false, /*craft=*/false);
            if (!hasVis) {
                player.addChatMessage(new ChatComponentText("§cНедостаточно аспектов: §7" + formatAspects(cost)));
                event.setCanceled(true);
                return;
            }

            boolean formed = mb.createStructure(event.world, event.x, event.y, event.z, event.face, player);
            if (!formed) {
                event.setCanceled(true);
                return;
            }

            boolean spent = wand.consumeAllVis(held, player, cost, /*doit=*/true, /*craft=*/false);
            if (!spent) {

                player.addChatMessage(new ChatComponentText("§cНе удалось списать аспекты после сборки."));
            } else {
                player.addChatMessage(new ChatComponentText("§a" + mb.getUniqueName() + " активирован магией!"));
            }

            event.setCanceled(true);
            return;
        }
    }

    private static String formatAspects(AspectList list) {
        StringBuilder sb = new StringBuilder();
        for (Aspect a : list.getAspects()) {
            if (a == null) continue;
            int amt = list.getAmount(a);
            if (amt <= 0) continue;
            if (sb.length() > 0) sb.append(", ");
            sb.append(a.getName()).append(": ").append(amt);
        }
        return sb.toString();
    }
}
