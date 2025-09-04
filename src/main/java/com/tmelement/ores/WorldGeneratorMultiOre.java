package com.tmelement.ores;

import com.tmelement.TMElementCore;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.*;

public class WorldGeneratorMultiOre implements IWorldGenerator {

    private static class OreData {
        public final Block oreBlock;
        public final int minY;
        public final int maxY;
        public final int minVeinSize;
        public final int maxVeinSize;
        public final int veinsPerChunk;
        public final Set<Class<? extends BiomeGenBase>> allowedBiomes;

        public OreData(Block oreBlock, int minY, int maxY, int minVeinSize, int maxVeinSize, int veinsPerChunk,
                       Set<Class<? extends BiomeGenBase>> allowedBiomes) {
            this.oreBlock = oreBlock;
            this.minY = minY;
            this.maxY = maxY;
            this.minVeinSize = minVeinSize;
            this.maxVeinSize = maxVeinSize;
            this.veinsPerChunk = veinsPerChunk;
            this.allowedBiomes = allowedBiomes;
        }

        public boolean canSpawnIn(BiomeGenBase biome) {
            if (allowedBiomes == null || allowedBiomes.isEmpty()) return true; // если список пустой — значит можно везде
            return allowedBiomes.contains(biome.getClass());
        }
    }

    private final List<OreData> ores = Arrays.asList(
            new OreData(TMElementCore.rareOre, 10, 46, 2, 8, 3, null),
            new OreData(TMElementCore.tungustenOre, 5, 30, 3, 7, 4, null),

            // lithiumOre — спавнится только в пустыне и в равнинах
            new OreData(TMElementCore.lithiumOre, 20, 60, 4, 10, 2,
                    new HashSet<>(Arrays.asList(
                            BiomeGenDesert.class
                    ))
            ),

            new OreData(TMElementCore.titanOre, 15, 50, 1, 5, 5, null),
            new OreData(TMElementCore.vanadiumOre, 12, 45, 2, 6, 3, null),
            new OreData(TMElementCore.californiumOre, 12, 45, 2, 6, 3, null),
            new OreData(TMElementCore.berkeliumOre, 12, 45, 2, 6, 3, null),
            new OreData(TMElementCore.einsteiniumOre, 12, 45, 2, 6, 3, null),
            new OreData(TMElementCore.neptuniumOre, 12, 45, 2, 6, 3, null),
            new OreData(TMElementCore.thoriumOre, 12, 45, 2, 6, 3, null)
    );

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
                         IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 0) { // только в обычном мире
            int baseX = chunkX * 16;
            int baseZ = chunkZ * 16;

            for (OreData ore : ores) {
                generateOre(ore, world, random, baseX, baseZ);
            }
        }
    }

    private void generateOre(OreData ore, World world, Random random, int baseX, int baseZ) {
        for (int i = 0; i < ore.veinsPerChunk; i++) {
            int posX = baseX + random.nextInt(16);
            int posY = ore.minY + random.nextInt(ore.maxY - ore.minY);
            int posZ = baseZ + random.nextInt(16);

            BiomeGenBase biome = world.getBiomeGenForCoords(posX, posZ);
            if (!ore.canSpawnIn(biome)) {
                continue;
            }

            int veinSize = ore.minVeinSize + random.nextInt(ore.maxVeinSize - ore.minVeinSize + 1);
            new WorldGenMinable(ore.oreBlock, veinSize).generate(world, random, posX, posY, posZ);
        }
    }
}