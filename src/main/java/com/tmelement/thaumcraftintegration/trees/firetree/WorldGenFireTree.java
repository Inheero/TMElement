package com.tmelement.thaumcraftintegration.trees.firetree;

import com.tmelement.TMElementCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class WorldGenFireTree extends WorldGenAbstractTree {
    private final int minTreeHeight;
    private final int randomTreeHeight;
    private final boolean worldgen;

    public WorldGenFireTree(boolean doBlockNotify, int minTreeHeight, int randomTreeHeight) {
        super(doBlockNotify);
        this.worldgen = !doBlockNotify;
        this.minTreeHeight = minTreeHeight;
        this.randomTreeHeight = randomTreeHeight;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int height = rand.nextInt(randomTreeHeight) + minTreeHeight;

        // Проверка места для дерева
        if (y < 1 || y + height + 1 > 256) {
            return false;
        }

        // Проверка свободного пространства
        for (int iy = y; iy <= y + 1 + height; iy++) {
            int spread = 1;
            if (iy == y) spread = 0;
            if (iy >= y + 1 + height - 2) spread = 3;

            for (int ix = x - spread; ix <= x + spread; ix++) {
                for (int iz = z - spread; iz <= z + spread; iz++) {
                    if (iy >= 0 && iy < 256) {
                        Block block = world.getBlock(ix, iy, iz);
                        if (!block.isAir(world, ix, iy, iz) &&
                                !block.isLeaves(world, ix, iy, iz) &&
                                !block.isReplaceable(world, ix, iy, iz) &&
                                iy > y) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        // Проверка почвы
        Block soil = world.getBlock(x, y - 1, z);
        if (!soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)TMElementCore.fireSapling)) {
            return false;
        }

        // Подготовка почвы
        soil.onPlantGrow(world, x, y - 1, z, x, y, z);

        // Генерация кроны
        int startY = y + height - 5;
        int endY = y + height + 3 + rand.nextInt(3);

        for (int iy = startY; iy <= endY; iy++) {
            int centerY = MathHelper.clamp_int(iy, y + height - 3, y + height);

            for (int ix = x - 5; ix <= x + 5; ix++) {
                for (int iz = z - 5; iz <= z + 5; iz++) {
                    double dx = ix - x;
                    double dy = iy - centerY;
                    double dz = iz - z;
                    double dist = dx*dx + dy*dy + dz*dz;

                    if (dist < 10 + rand.nextInt(8) &&
                            world.getBlock(ix, iy, iz).canBeReplacedByLeaves(world, ix, iy, iz)) {
                        setBlockAndNotify(world, ix, iy, iz, TMElementCore.fireLeaves, 0);
                    }
                }
            }
        }

        // Генерация ствола и веток
        int chance = (int)(height * 1.5);
        boolean lastBlockWasLog = false;

        for (int iy = 0; iy < height; iy++) {
            Block block = world.getBlock(x, y + iy, z);

            if (block.isAir(world, x, y + iy, z) ||
                    block.isLeaves(world, x, y + iy, z) ||
                    block.isReplaceable(world, x, y + iy, z)) {

                if (iy > 0 && !lastBlockWasLog && rand.nextInt(chance) == 0) {
                    // Специальные блоки веток
                    setBlockAndNotify(world, x, y + iy, z, TMElementCore.fireLog, 2);
                    chance += height;
                    lastBlockWasLog = true;
                } else {
                    // Основной ствол
                    setBlockAndNotify(world, x, y + iy, z, TMElementCore.fireLog, 1);
                    lastBlockWasLog = false;
                }

                // Боковые ветки
                setBlockAndNotify(world, x - 1, y + iy, z, TMElementCore.fireLog, 1);
                setBlockAndNotify(world, x + 1, y + iy, z, TMElementCore.fireLog, 1);
                setBlockAndNotify(world, x, y + iy, z - 1, TMElementCore.fireLog, 1);
                setBlockAndNotify(world, x, y + iy, z + 1, TMElementCore.fireLog, 1);
            }
        }

        // Верхушка дерева
        setBlockAndNotify(world, x, y + height, z, TMElementCore.fireLog, 1);

        // Основание с корнями
        setBlockAndNotify(world, x - 1, y, z - 1, TMElementCore.fireLog, 1);
        setBlockAndNotify(world, x + 1, y, z + 1, TMElementCore.fireLog, 1);
        setBlockAndNotify(world, x - 1, y, z + 1, TMElementCore.fireLog, 1);
        setBlockAndNotify(world, x + 1, y, z - 1, TMElementCore.fireLog, 1);

        // Дополнительные ветки у основания
        if (rand.nextInt(3) != 0) setBlockAndNotify(world, x - 1, y + 1, z - 1, TMElementCore.fireLog, 1);
        if (rand.nextInt(3) != 0) setBlockAndNotify(world, x + 1, y + 1, z + 1, TMElementCore.fireLog, 1);
        if (rand.nextInt(3) != 0) setBlockAndNotify(world, x - 1, y + 1, z + 1, TMElementCore.fireLog, 1);
        if (rand.nextInt(3) != 0) setBlockAndNotify(world, x + 1, y + 1, z - 1, TMElementCore.fireLog, 1);

        // Длинные горизонтальные ветки
        setBlockAndNotify(world, x - 2, y, z, TMElementCore.fireLog, 5); // Запад
        setBlockAndNotify(world, x + 2, y, z, TMElementCore.fireLog, 5); // Восток
        setBlockAndNotify(world, x, y, z - 2, TMElementCore.fireLog, 9); // Север
        setBlockAndNotify(world, x, y, z + 2, TMElementCore.fireLog, 9); // Юг

        return true;
    }

    private void setBlockAndNotify(World world, int x, int y, int z, Block block, int meta) {
        world.setBlock(x, y, z, block, meta, worldgen ? 3 : 2);
    }
}