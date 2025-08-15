package com.tmelement.thaumcraftintegration;

import com.tmelement.thaumcraftintegration.trees.aertree.WorldGenAerTree;
import com.tmelement.thaumcraftintegration.trees.firetree.WorldGenFireTree;
import com.tmelement.thaumcraftintegration.trees.terratree.WorldGenTerraTree;
import com.tmelement.thaumcraftintegration.trees.watertree.WorldGenWaterTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import thaumcraft.common.lib.world.biomes.BiomeGenMagicalForest;

import java.util.Random;

public class BiomeGenElvenForest extends BiomeGenMagicalForest {

    private final WorldGenWaterTree waterTree;
    private final WorldGenAerTree aerTree;
    private final WorldGenTerraTree terraTree;
    private final WorldGenFireTree ignisTree;

    public BiomeGenElvenForest(int id) {
        super(id);

        this.waterTree = new WorldGenWaterTree(false, 6, 4);   // Пример: minHeight=6, maxHeight=4
        this.aerTree = new WorldGenAerTree(false, 5, 3);       // Настройте параметры под ваши нужды
        this.terraTree = new WorldGenTerraTree(false, 7, 5);
        this.ignisTree = new WorldGenFireTree(false, 5, 4);

        this.setBiomeName("Elven Forest");
        this.setColor(0x4CAF50);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random) {
        int chance = random.nextInt(20);

        if (chance < 5) {
            return waterTree;
        } else if (chance < 10) {
            return aerTree;
        } else if (chance < 15) {
            return terraTree;
        } else {
            return ignisTree;
        }
    }

    @Override
    public void func_76728_a(World world, Random random, int x, int z) {
        super.func_76728_a(world, random, x, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int func_150558_b(int x, int y, int z) {
        return 0x4CAF50;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int func_150571_c(int x, int y, int z) {
        return 0x388E3C;
    }

    @Override
    public int getWaterColorMultiplier() {
        return 0x3F51B5;
    }
}