package com.example.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.util.BufferAllocator;

@Mixin(BufferAllocator.class)
public interface ByteBufferBuilderAccessor {
	
	@Accessor("pointer")
	long testmod$getPointer();
	
	@Accessor("size")
	int testmod$getCapacity();
	
	
	
}
