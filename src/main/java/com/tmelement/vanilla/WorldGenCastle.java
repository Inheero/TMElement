package com.tmelement.vanilla;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;

import java.io.InputStream;
import java.util.Random;

public class WorldGenCastle implements IWorldGenerator {

    private NBTTagCompound schematicNBT;

    public WorldGenCastle() {
        try {

            InputStream in = MinecraftServer.class.getResourceAsStream("/assets/tmelements/structures/castle.schematic");
            schematicNBT = CompressedStreamTools.readCompressed(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world,
                         IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        if (world.provider.dimensionId != 0) return;

        int x = chunkX * 16 + rand.nextInt(16);
        int z = chunkZ * 16 + rand.nextInt(16);
        int y = world.getTopSolidOrLiquidBlock(x, z);

        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        if (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE)) return;

        if (rand.nextInt(50) != 0) return;

        placeStructure(world, x, y, z);
    }

    private void placeStructure(World world, int originX, int originY, int originZ) {
        if (schematicNBT == null) return;

        short width = schematicNBT.getShort("Width");
        short height = schematicNBT.getShort("Height");
        short length = schematicNBT.getShort("Length");

        byte[] blocks = schematicNBT.getByteArray("Blocks");
        byte[] data   = schematicNBT.getByteArray("Data");

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < length; z++) {
                for (int x = 0; x < width; x++) {
                    int index = y * width * length + z * width + x;
                    Block block = Block.getBlockById(blocks[index] & 0xFF);
                    int meta = data[index] & 0xFF;

                    if (block != null) {
                        world.setBlock(originX + x, originY + y, originZ + z, block, meta, 2);
                    }
                }
            }
        }
    }
}
