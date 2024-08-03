package com.example;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomBlock extends BlockWithEntity{

	public CustomBlock(Settings settings) {
		super(settings);
	}
	
	

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CustomBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return null;
	}
	
	 protected BlockRenderType getRenderType(BlockState state) {
	      return BlockRenderType.INVISIBLE;// 24
	 }
	
	

}
