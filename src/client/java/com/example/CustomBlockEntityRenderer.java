package com.example;

import org.joml.Matrix4f;

import net.minecraft.block.WoodType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.ColorHelper.Argb;

public class CustomBlockEntityRenderer implements BlockEntityRenderer<CustomBlockEntity>{
	
	public CustomBlockEntityRenderer(BlockEntityRendererFactory.Context con) {
		
	}

	@Override
	public void render(CustomBlockEntity entity, float tickDelta, MatrixStack matrices,
			VertexConsumerProvider vertexConsumers, int light, int overlay) {
		
		VertexConsumer cons = vertexConsumers.getBuffer(RenderLayer.getBlockLayers().get(0));
		Matrix4f matrix4f = matrices.peek().getPositionMatrix();// 20
		
		for(int i=0;i<50000;i++) {
			cons.vertex(matrix4f, 0.0f + i * 0.1f, 0.0f + i * 0.1f, 0.0f + i * 0.1f).color(1, 0, 0, 0).texture(0, 0).overlay(0).light(0).normal(0, 1, 0);
			cons.vertex(matrix4f, 1.0f + i * 0.1f, 0.0f + i * 0.1f, 0.0f + i * 0.1f).color(0, 1, 0, 0).texture(1, 0).overlay(1).light(0).normal(0, 1, 0);
			cons.vertex(matrix4f, 0.0f + i * 0.1f, 1.0f + i * 0.1f, 1.0f + i * 0.1f).color(0, 0, 1, 0).texture(0, 1).overlay(0).light(1).normal(0, 1, 0);
			cons.vertex(matrix4f, 1.0f + i * 0.1f, 1.0f + i * 0.1f, 1.0f + i * 0.1f).color(1, 0, 1, 0).texture(1, 1).overlay(1).light(1).normal(0, 1, 0);
		}
		
		
	}

}
