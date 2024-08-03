package com.example.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.ExampleMod;
import com.mojang.logging.LogUtils;

import net.minecraft.client.util.BufferAllocator;

@Mixin(BufferAllocator.class)
public class ByteBufferBuilderMixin {
	
	@Shadow
	private long pointer;
	
	@Inject(method = "grow", at = @At("TAIL"))
	private void testmod$grow(int cap, CallbackInfo clb) {
		if(!ExampleMod.DEBUG)
			return;
		
		LogUtils.getLogger().info("testmod$grow: this = "+this+"; pointer = "+Long.toHexString(pointer));
	}
	
}
