package com.tmelement.biomes;

import com.tmelement.TMElementCore;
import com.tmelement.blocks.BlockBamboo;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeBambooForest extends BiomeGenBase {
    public BiomeBambooForest(int id) {
        super(id);

        setBiomeName("Bamboo Forest");
        setColor(0x7BA331);
        setTemperatureRainfall(0.8F, 0.9F);

        topBlock = Blocks.grass;
        fillerBlock = Blocks.dirt;

        // Оптимальная плотность
        theBiomeDecorator.treesPerChunk = 6;
        theBiomeDecorator.grassPerChunk = 6;
        theBiomeDecorator.flowersPerChunk = 1;
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        return new WorldGenBamboo((BlockBamboo)TMElementCore.bamboo);
    }
}