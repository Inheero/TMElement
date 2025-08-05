package com.tmelement.biomes;

import com.tmelement.TMElementCore;
import com.tmelement.blocks.BlockBamboo;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenBamboo  extends WorldGenAbstractTree {
    private final BlockBamboo bambooBlock;

    public WorldGenBamboo(BlockBamboo bamboo) {
        super(false);
        this.bambooBlock = bamboo;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        for (int i = 0; i < 5; i++) {
            int xPos = x + rand.nextInt(8) - rand.nextInt(8);
            int zPos = z + rand.nextInt(8) - rand.nextInt(8);
            int yPos = world.getHeightValue(xPos, zPos);

            if (world.getBlock(xPos, yPos-1, zPos) == Blocks.grass) {
                // Генерируем столб 5-7 блоков
                int height = 5 + rand.nextInt(3);
                for (int j = 0; j < height; j++) {
                    world.setBlock(xPos, yPos+j, zPos, TMElementCore.bamboo, 0, 2);
                }
            }
        }
        return true;
    }
}