package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererFactories.register(ExampleMod.CUSTOM_BLOCK_ENTITY, CustomBlockEntityRenderer::new);
	}
}