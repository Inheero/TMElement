package com.tmelement.biomes;

import com.tmelement.proxy.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class BambooRenderer implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        // Упрощенный рендер для инвентаря
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        // Используем отдельный Tessellator для инвентаря
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

        // Рендерим упрощенную модель
        renderSimpleBlock(block, metadata, renderer);

        tessellator.draw();
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        // Не используем Tessellator напрямую в мировом рендере!
        renderer.setRenderBounds(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
        return renderer.renderStandardBlock(block, x, y, z);
    }

    private void renderSimpleBlock(Block block, int metadata, RenderBlocks renderer) {
        renderer.setRenderBounds(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);

        // Рендерим все стороны для инвентаря
        renderer.renderMinY = 0.0F;
        renderer.renderMaxY = 1.0F;
        renderer.renderMinX = 0.3F;
        renderer.renderMaxX = 0.7F;
        renderer.renderMinZ = 0.3F;
        renderer.renderMaxZ = 0.7F;

        Tessellator.instance.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0, block.getIcon(0, metadata));

        Tessellator.instance.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0, 0.0, 0.0, block.getIcon(1, metadata));

        Tessellator.instance.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0, block.getIcon(2, metadata));

        Tessellator.instance.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0, 0.0, 0.0, block.getIcon(3, metadata));

        Tessellator.instance.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0, block.getIcon(4, metadata));

        Tessellator.instance.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0, 0.0, 0.0, block.getIcon(5, metadata));
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return ClientProxy.bambooRenderID;
    }
}
