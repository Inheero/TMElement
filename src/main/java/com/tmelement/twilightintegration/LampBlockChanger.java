package com.tmelement.twilightintegration;

import com.tmelement.TMElementCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.block.Block;
import vazkii.botania.common.block.ModBlocks;

import java.util.Random;

public class LampBlockChanger {

    private static final Random rand = new Random();

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        World world = event.world;
        int x = event.x;
        int y = event.y;
        int z = event.z;

        ItemStack held = player.getCurrentEquippedItem();
        if (held == null || !(held.getItem() instanceof twilightforest.item.ItemTFLampOfCinders)) return;

        Block target = world.getBlock(x, y, z);

        if (target == ModBlocks.livingrock) {
            if (!world.isRemote) {
                world.setBlock(x, y, z, TMElementCore.SCORECHEDLIFESTONE, world.getBlockMetadata(x, y, z), 2);
            }
        } else if (target == ModBlocks.livingwood) {
            if (!world.isRemote) {
                world.setBlock(x, y, z, TMElementCore.SCORECHEDLIFEWOOD, world.getBlockMetadata(x, y, z), 2);
            }
        } else {
            return;
        }

        if (world.isRemote) {
            for (int i = 0; i < 10; i++) {
                double dx = x + 0.5 + (world.rand.nextDouble() - 0.5);
                double dy = y + 0.5 + (world.rand.nextDouble() - 0.5);
                double dz = z + 0.5 + (world.rand.nextDouble() - 0.5);
                world.spawnParticle("smoke", dx, dy, dz, 0, 0, 0);
                world.spawnParticle("flame", dx, dy, dz, 0, 0, 0);
            }

            world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "mob.ghast.fireball", 0.5F, 1.5F);
        }

        event.setCanceled(true);
    }
}
