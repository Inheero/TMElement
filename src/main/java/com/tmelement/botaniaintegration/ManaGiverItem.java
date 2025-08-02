package com.tmelement.botaniaintegration;

import com.tmelement.TMElementCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.Botania;

import java.util.List;

public class ManaGiverItem extends Item implements IManaDissolvable {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ManaGiverItem() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(TMElementCore.tab);
        this.setUnlocalizedName("tmelements.mana_giver");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        icons = new IIcon[15];
        icons[0] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_zombie");
        icons[1] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_spider");
        icons[2] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_creeper");
        icons[3] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_cavespider");
        icons[4] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_endermen");
        icons[5] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_ghast");
        icons[6] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_ifrite");
        icons[7] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_magmacube");
        icons[8] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_silverfish");
        icons[9] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_skelet");
        icons[10] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_slime");
        icons[11] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_witch");
        icons[12] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_witcherskelet");
        icons[13] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_wolf");
        icons[14] = reg.registerIcon(TMElementCore.MODID +":mana_giver/by_zombiepigman");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return icons[MathHelper.clamp_int(meta, 0, icons.length - 1)];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 1:
                return "item.mymodid.mana_giver.by_spider";
            case 2:
                return "item.mymodid.mana_giver.by_creeper";
            case 3:
                return "item.mymodid.mana_giver.by_cavespider";
            case 4:
                return "item.mymodid.mana_giver.by_endermen";
            case 5:
                return "item.mymodid.mana_giver.by_ghast";
            case 6:
                return "item.mymodid.mana_giver.by_ifrite";
            case 7:
                return "item.mymodid.mana_giver.by_magmacube";
            case 8:
                return "item.mymodid.mana_giver.by_silverfish";
            case 9:
                return "item.mymodid.mana_giver.by_skelet";
            case 10:
                return "item.mymodid.mana_giver.by_slime";
            case 11:
                return "item.mymodid.mana_giver.by_witch";
            case 12:
                return "item.mymodid.mana_giver.by_witcherskelet";
            case 13:
                return "item.mymodid.mana_giver.by_wolf";
            case 14:
                return "item.mymodid.mana_giver.by_zombiepigman";
            default:
                return "item.mymodid.mana_giver.by_zombie";
        }
    }

    @Override
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < 15; ++i)
            p_150895_3_.add(new ItemStack(this, 1, i));
    }

    @SubscribeEvent
    public void onDrop(LivingDropsEvent event) {
        if (event.entity instanceof EntityPigZombie) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 14)));
        } else if (event.entity instanceof EntityZombie) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 0)));
        }else if (event.entity instanceof EntityCaveSpider) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 3)));
        }else if (event.entity instanceof EntitySpider) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 1)));
        } else if (event.entity instanceof EntityCreeper) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 2)));
        } else if (event.entity instanceof EntityEnderman) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 4)));
        } else if (event.entity instanceof EntityGhast) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 5)));
        } else if (event.entity instanceof EntityBlaze) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 6)));
        } else if (event.entity instanceof EntityMagmaCube) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 7)));
        } else if (event.entity instanceof EntitySilverfish) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 8)));
        } else if (event.entity instanceof EntitySkeleton) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, ((EntitySkeleton) event.entity).getSkeletonType() == 1 ? 9 : 13)));
        } else if (event.entity instanceof EntitySlime) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 10)));
        } else if (event.entity instanceof EntityWitch) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 11)));
        } else if (event.entity instanceof EntityWolf) {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(this, 1, 13)));
        }
    }

    @Override
    public void onDissolveTick(IManaPool pool, ItemStack stack, EntityItem item) {
        if (!pool.isFull() && pool.getCurrentMana() != 0) {
            TileEntity tile = (TileEntity) pool;
            int manaPerItem;
            switch (stack.getItemDamage()) {
                case 0:
                    manaPerItem = 100;
                    break;
                case 1:
                    manaPerItem = 110;
                    break;
                case 2:
                    manaPerItem = 120;
                    break;
                case 3:
                    manaPerItem = 130;
                    break;
                case 4:
                    manaPerItem = 140;
                    break;
                case 5:
                    manaPerItem = 150;
                    break;
                case 6:
                    manaPerItem = 160;
                    break;
                case 7:
                    manaPerItem = 170;
                    break;
                case 8:
                    manaPerItem = 180;
                    break;
                case 9:
                    manaPerItem = 190;
                    break;
                case 10:
                    manaPerItem = 200;
                    break;
                case 11:
                    manaPerItem = 210;
                    break;
                case 12:
                    manaPerItem = 220;
                    break;
                case 13:
                    manaPerItem = 230;
                    break;
                case 14:
                    manaPerItem = 240;
                    break;
                default:
                    return;
            }

            if (!item.worldObj.isRemote) {
                pool.recieveMana(manaPerItem);
                --stack.stackSize;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(item.worldObj, tile.xCoord, tile.yCoord, tile.zCoord);
            }

            for (int i = 0; i < 50; ++i) {
                float r = (float) Math.random() * 0.25F;
                float g = 0.0F;
                float b = (float) Math.random() * 0.25F;
                float s = 0.45F * (float) Math.random() * 0.25F;
                float m = 0.045F;
                float mx = ((float) Math.random() - 0.5F) * m;
                float my = (float) Math.random() * m;
                float mz = ((float) Math.random() - 0.5F) * m;
                Botania.proxy.wispFX(item.worldObj, item.posX, (float) tile.yCoord + 0.5F, item.posZ, r, g, b, s, mx, my, mz);
            }
        }
    }
}

