package com.tmelement.ores;

import com.tmelement.TMElementCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockRareOre extends Block {
    public BlockRareOre() {
        super(Material.rock);
        this.setBlockName("rareOre");
        this.setBlockTextureName(TMElementCore.MODID +":rare_ore");
        this.setCreativeTab(TMElementCore.tab);
        this.setHardness(3.0F);  // Уровень твердости
        this.setResistance(5.0F);  // Уровень взрывостойкости
        this.setHarvestLevel("pickaxe", 3);  // Требуемый уровень инструмента: 2 - алмазная кирка и выше
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(this);  // Возвращает сам блок руды при добыче
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;  // Количество выпадающих предметов при добыче
    }
}

