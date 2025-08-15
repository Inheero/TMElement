package com.tmelement;

import com.tmelement.thaumcraftintegration.BiomeGenElvenForest;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeRegistry {

    public static int elvenForestBiomeID = 250;

    public static BiomeGenBase elvenForest;

    public static void registerBiomes() {

        elvenForest = new BiomeGenElvenForest(elvenForestBiomeID)
                .setColor(0x4CAF50) // Цвет биома на карте
                .setTemperatureRainfall(0.7F, 0.8F); // Температура и влажность


        BiomeDictionary.registerBiomeType(elvenForest, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(elvenForest, 10)); // Вес биома при генерации
    }
}