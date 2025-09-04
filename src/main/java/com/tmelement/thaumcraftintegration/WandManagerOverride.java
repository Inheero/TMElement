package com.tmelement.thaumcraftintegration;

import ml.luxinfine.hooks.api.HooksContainer;
import ml.luxinfine.hooks.api.IHookContext;
import ml.luxinfine.hooks.api.Inject;
import ml.luxinfine.hooks.api.InjectTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;

@HooksContainer(targetClassRef = WandManager.class)
public class WandManagerOverride {

    @Inject(target = InjectTarget.HEAD)
    public static void createArcaneFurnace(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, IHookContext context) {

        ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();

        for (int xx = x - 2; xx <= x; ++xx) {
            for (int yy = y - 2; yy <= y; ++yy) {
                for (int zz = z - 2; zz <= z; ++zz) {
                    AspectList required = new AspectList()
                            .add(Aspect.FIRE, 25)
                            .add(Aspect.EARTH, 25);

                    if (WandManager.fitArcaneFurnace(world, xx, yy, zz) &&
                            wand.consumeAllVisCrafting(itemstack, player, required, true)) {
                        if (!world.isRemote) {
                            WandManager.replaceArcaneFurnace(world, xx, yy, zz);
                            context.exit(true);
                            return;
                        }
                        context.exit(false);
                        return;
                    }
                }
            }
        }
        context.exit(false);
    }
}