package com.example;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class CustomBlockEntity extends BlockEntity{

	public CustomBlockEntity(BlockPos pos, BlockState state) {
		super(ExampleMod.CUSTOM_BLOCK_ENTITY, pos, state);
	}

}
