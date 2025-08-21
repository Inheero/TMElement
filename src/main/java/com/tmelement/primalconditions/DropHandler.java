package com.tmelement.primalconditions;

import com.tmelement.TMElementCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.Iterator;

public class DropHandler  {

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        // Обработка коров
        if (event.entityLiving instanceof EntityCow) {
            Iterator<EntityItem> iterator = event.drops.iterator();
            boolean foundLeather = false;

            while (iterator.hasNext()) {
                net.minecraft.entity.item.EntityItem drop = iterator.next();
                ItemStack stack = drop.getEntityItem();

                // Удаляем обычную кожу
                if (stack.getItem() == Items.leather) {
                    iterator.remove();
                    foundLeather = true;
                }
            }

            // Добавляем кастомный предмет вместо кожи
            if (foundLeather) {
                ItemStack customDrop = new ItemStack(TMElementCore.peltCow, 1);
                net.minecraft.entity.item.EntityItem entityItem = new net.minecraft.entity.item.EntityItem(
                        event.entity.worldObj,
                        event.entity.posX,
                        event.entity.posY,
                        event.entity.posZ,
                        customDrop
                );
                event.drops.add(entityItem);
            }
        }
        // Обработка лошадей
        else if (event.entityLiving instanceof EntityHorse) {
            Iterator<net.minecraft.entity.item.EntityItem> iterator = event.drops.iterator();

            while (iterator.hasNext()) {
                net.minecraft.entity.item.EntityItem drop = iterator.next();
                ItemStack stack = drop.getEntityItem();

                // Удаляем всю кожу
                if (stack.getItem() == Items.leather) {
                    iterator.remove();
                }
            }
        }
    }
}

