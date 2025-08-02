package com.tmelement.fluids;

import com.tmelement.TMElementCore;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
import ml.luxinfine.helper.integrations.DefaultIntegrations;
import ml.luxinfine.helper.registration.RegistrableObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.UUID;

public class ItemWoodBucket extends ItemBucket {
    private Block isFull;

    public ItemWoodBucket(Block liquidBlock) {
        super(liquidBlock);
        this.isFull = liquidBlock;
        setCreativeTab(TMElementCore.tab);
    }

    private static ItemWoodBucket emptyBucket;
    private static ItemWoodBucket waterBucket;

    @RegistrableObject

    private static void init() {
        ItemWoodBucket empty_bukkit = new ItemWoodBucket(Blocks.air);
        emptyBucket = empty_bukkit;
        emptyBucket.setTextureName(TMElementCore.MODID + ":" +"mymodwood_bucket");
        emptyBucket.setUnlocalizedName("mymod.wood_bucket");

        ItemWoodBucket water_bukkit = new ItemWoodBucket(Blocks.water);
        waterBucket = water_bukkit;
        waterBucket.setTextureName(TMElementCore.MODID + ":" +"mymodwood_bucket_water");
        waterBucket.setUnlocalizedName("mymod.wood_bucket_water");
        waterBucket.setContainerItem(emptyBucket);

        GameRegistry.registerItem(emptyBucket, "wood_bucket");
        GameRegistry.registerItem(waterBucket, "wood_bucket_water");

        FluidContainerRegistry.registerFluidContainer(new FluidStack(FluidRegistry.WATER, 1000), new ItemStack(waterBucket), new ItemStack(emptyBucket));
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);

        if (movingobjectposition == null) {
            return itemStack;
        } else {
            FillBucketEvent event = new FillBucketEvent(player, itemStack, world, movingobjectposition);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return itemStack;
            }

            if (event.getResult() == Event.Result.ALLOW) {
                if (player.capabilities.isCreativeMode) {
                    return itemStack;
                }

                if (--itemStack.stackSize <= 0) {
                    return event.result;
                }

                if (!player.inventory.addItemStackToInventory(event.result)) {
                    player.dropPlayerItemWithRandomChoice(event.result, false);
                }

                return itemStack;
            }

            int x = movingobjectposition.blockX;
            int y = movingobjectposition.blockY;
            int z = movingobjectposition.blockZ;
            UUID playerUUID = player.getPersistentID();

            if (!world.canMineBlock(player, x, y, z)) {
                return itemStack;
            }

            if (flag) {
                if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, itemStack)) {
                    return itemStack;
                }

                Material material = world.getBlock(x, y, z).getMaterial();
                int l = world.getBlockMetadata(x, y, z);

                if (material == Material.water && l == 0) {
                    if (!world.isRemote) {
                        if (DefaultIntegrations.regions().canPlaceBlock(world, x, y, z, playerUUID)) {
                            world.setBlockToAir(x, y, z);
                            return this.func_150910_a(itemStack, player, ItemWoodBucket.waterBucket);
                        } else {
                            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("infinityitems.tooltips.nofill")));
                        }
                    }
                }
            } else {
                if (this.isFull == Blocks.air) {
                    return new ItemStack(ItemWoodBucket.emptyBucket);
                }

                if (movingobjectposition.sideHit == 0) {
                    --y;
                }

                if (movingobjectposition.sideHit == 1) {
                    ++y;
                }

                if (movingobjectposition.sideHit == 2) {
                    --z;
                }

                if (movingobjectposition.sideHit == 3) {
                    ++z;
                }

                if (movingobjectposition.sideHit == 4) {
                    --x;
                }

                if (movingobjectposition.sideHit == 5) {
                    ++x;
                }

                if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, itemStack)) {
                    return itemStack;
                }
                if (!world.isRemote) {
                    if (DefaultIntegrations.regions().canPlaceBlock(world, x, y, z, playerUUID)) {
                        if (this.tryPlaceContainedLiquid(world, x, y, z) && !player.capabilities.isCreativeMode) {
                            return new ItemStack(ItemWoodBucket.emptyBucket);
                        }
                    } else {
                        player.addChatMessage((new ChatComponentText(StatCollector.translateToLocalFormatted("infinityitems.tooltips.dofill"))));
                    }
                }
            }
            return itemStack;
        }
    }

    private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_) {
        if (p_150910_2_.capabilities.isCreativeMode) {
            return p_150910_1_;
        } else if (--p_150910_1_.stackSize <= 0) {
            return new ItemStack(p_150910_3_);
        } else {
            if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(p_150910_3_))) {
                p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(p_150910_3_, 1, 0), false);
            }

            return p_150910_1_;
        }
    }

    public boolean tryPlaceContainedLiquid(World p_77875_1_, int p_77875_2_, int p_77875_3_, int p_77875_4_) {
        if (this.isFull == Blocks.air) {
            return false;
        } else {
            Material material = p_77875_1_.getBlock(p_77875_2_, p_77875_3_, p_77875_4_).getMaterial();
            boolean flag = !material.isSolid();

            if (!p_77875_1_.isAirBlock(p_77875_2_, p_77875_3_, p_77875_4_) && !flag) {
                return false;
            } else {
                if (p_77875_1_.provider.isHellWorld && this.isFull == Blocks.flowing_water) {
                    p_77875_1_.playSoundEffect((double) ((float) p_77875_2_ + 0.5F), (double) ((float) p_77875_3_ + 0.5F), (double) ((float) p_77875_4_ + 0.5F), "random.fizz", 0.5F, 2.6F + (p_77875_1_.rand.nextFloat() - p_77875_1_.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l) {
                        p_77875_1_.spawnParticle("largesmoke", (double) p_77875_2_ + Math.random(), (double) p_77875_3_ + Math.random(), (double) p_77875_4_ + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                } else {
                    if (!p_77875_1_.isRemote && flag && !material.isLiquid()) {
                        p_77875_1_.func_147480_a(p_77875_2_, p_77875_3_, p_77875_4_, true);
                    }

                    p_77875_1_.setBlock(p_77875_2_, p_77875_3_, p_77875_4_, this.isFull, 0, 3);
                }

                return true;
            }
        }
    }
}

