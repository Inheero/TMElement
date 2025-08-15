package com.tmelement.thaumcraftintegration;

import com.tmelement.ModItems;
import ml.luxinfine.hooks.api.HooksContainer;
import ml.luxinfine.hooks.api.IHookContext;
import ml.luxinfine.hooks.api.Inject;
import ml.luxinfine.hooks.api.InjectTarget;
import net.aetherteam.aether.items.AetherItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import thaumcraft.common.tiles.TileArcaneFurnace;

@HooksContainer(targetClassRef = TileArcaneFurnace.class)
public class ArcaneFurnaceExtension {

    @Inject(
            target = InjectTarget.AFTER_INVOKE,
            targetInfo = {"net/minecraft/item/crafting/FurnaceRecipes", "getSmeltingResult", "(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"}
    )
    public static ItemStack updateEntity(FurnaceRecipes callInstance, ItemStack callInput, TileArcaneFurnace furnace, IHookContext context) {
        return modifyOutput(callInput, (ItemStack) context.getRedirectedValue());
    }

    @Inject(
            target = InjectTarget.AFTER_INVOKE,
            targetInfo = {"net/minecraft/item/crafting/FurnaceRecipes", "getSmeltingResult", "(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"}
    )
    public static ItemStack canSmelt(FurnaceRecipes callInstance, ItemStack callInput, TileArcaneFurnace furnace, int slotIn, IHookContext context) {
        return modifyOutput(callInput, (ItemStack) context.getRedirectedValue());
    }

    private static ItemStack modifyOutput(ItemStack input, ItemStack originalOutput) {
        if (input.getItem() == AetherItems.ValkyrieBoots)
            originalOutput = new ItemStack(ModItems.MA_INGOTS, 4, 5);
        else if (input.getItem() == AetherItems.ValkyrieChestplate)
            originalOutput = new ItemStack(ModItems.MA_INGOTS, 8, 5);
        else if (input.getItem() == AetherItems.ValkyrieHelmet)
            originalOutput = new ItemStack(ModItems.MA_INGOTS, 5, 5);
        else if (input.getItem() == AetherItems.ValkyrieLeggings)
            originalOutput = new ItemStack(ModItems.MA_INGOTS, 7, 5);
        return originalOutput;
    }
}