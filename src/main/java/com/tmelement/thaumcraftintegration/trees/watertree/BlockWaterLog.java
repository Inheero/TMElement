package com.tmelement.thaumcraftintegration.trees.watertree;

import com.tmelement.TMElementCore;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWaterLog extends BlockLog {
    // Массив для хранения текстур
    private IIcon[] icons = new IIcon[3];

    public BlockWaterLog() {
        super();
        this.setBlockName("custom_log_water"); // Название блока
        this.setBlockTextureName(TMElementCore.MODID +":custom_log_water"); // Путь к текстуре
    }

    // Метод для регистрации текстур блока
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        // Регистрация текстуры для верхней и нижней стороны блока
        this.icons[0] = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.icons[1] = iconRegister.registerIcon(this.getTextureName() + "_top");
        // Регистрация текстуры для боковых сторон блока
        this.icons[2] = iconRegister.registerIcon(this.getTextureName() + "_side");
    }

    // Метод для получения текстуры в зависимости от стороны блока
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0 || side == 1) {
            return this.icons[0]; // Верх и низ
        } else {
            return this.icons[2]; // Боковые стороны
        }
    }
}

