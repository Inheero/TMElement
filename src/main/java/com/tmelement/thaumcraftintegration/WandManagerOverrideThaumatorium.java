package com.tmelement.thaumcraftintegration;

import cpw.mods.fml.common.network.NetworkRegistry;
import ml.luxinfine.hooks.api.HooksContainer;
import ml.luxinfine.hooks.api.IHookContext;
import ml.luxinfine.hooks.api.Inject;
import ml.luxinfine.hooks.api.InjectTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.tiles.TileThaumatorium;

@HooksContainer(targetClassRef = WandManager.class)
public class WandManagerOverrideThaumatorium {

    @Inject(target = InjectTarget.HEAD)
    public static void createThaumatorium(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, IHookContext context) {
        ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();

        AspectList required = new AspectList()
                .add(Aspect.FIRE, 100)   // тут ставишь свои аспекты
                .add(Aspect.ORDER, 10)
                .add(Aspect.WATER, 10)
                .add(Aspect.AIR, 20);  // можно добавить новые

        // если аспекты оплатились и сервер
        if (wand.consumeAllVisCrafting(itemstack, player, required, true) && !world.isRemote) {
            // полностью копируешь поведение из оригинала
            world.setBlock(x, y, z, ConfigBlocks.blockMetalDevice, 10, 0);
            world.setBlock(x, y + 1, z, ConfigBlocks.blockMetalDevice, 11, 0);

            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof TileThaumatorium) {
                ((TileThaumatorium) tile).facing = ForgeDirection.getOrientation(side);
            }

            world.markBlockForUpdate(x, y, z);
            world.markBlockForUpdate(x, y + 1, z);
            world.notifyBlockChange(x, y, z, ConfigBlocks.blockMetalDevice);
            world.notifyBlockChange(x, y + 1, z, ConfigBlocks.blockMetalDevice);

            PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x, y, z, -9999),
                    new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, 32.0));
            PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x, y + 1, z, -9999),
                    new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, 32.0));

            world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "thaumcraft:wand", 1.0F, 1.0F);
            context.exit(true);
            return;
        }

        context.exit(false);
    }
}