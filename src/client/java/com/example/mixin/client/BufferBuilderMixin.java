package com.example.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.ExampleMod;
import com.mojang.logging.LogUtils;

import net.irisshaders.iris.vertices.BufferBuilderPolygonView;
import net.irisshaders.iris.vertices.IrisVertexFormats;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormatElement;
import net.minecraft.client.util.BufferAllocator;

@Mixin(value = BufferBuilder.class, priority = 1500)
public abstract class BufferBuilderMixin{
	@Shadow
	private BufferAllocator allocator;

	@Shadow
	private int vertexCount;

	@Shadow
	private VertexFormat format;

	@Shadow
	private VertexFormat.DrawMode drawMode;

	@Shadow
	private boolean building;
	
	@Shadow
	private long vertexPointer;
	
	@Shadow
	@Final
	private int[] offsetsByElementId;

	
	@Inject(
			method = "endVertex()V",
			at = @At("HEAD")
	)
	private void testmod$beforeNext(CallbackInfo ci) {
		if(!ExampleMod.DEBUG)
			return;
		
		ByteBufferBuilderAccessor bbba = (ByteBufferBuilderAccessor)allocator;
		long begin = bbba.testmod$getPointer();
		long capacity = bbba.testmod$getCapacity();
		LogUtils.getLogger().info("BufferBuilder::endVertex Mixin testmod$beforeNext: this = "+this+"; ByteBufferBuilder = "+allocator+"; vertexCount = "+this.vertexCount+"; beginAddress = "+Long.toHexString(begin)+"; currentAddress = "+Long.toHexString(this.vertexPointer)+"; endAddress = "+Long.toHexString(begin+capacity)+"; capacity = "+capacity);
	}
	
	@Redirect(
			method = "fillExtendedData", 
			at = @At(
					value = "INVOKE",
					target = "Lnet/irisshaders/iris/vertices/BufferBuilderPolygonView;setup([JII)V"
			)
	)
	private void testmod$fillExtendedData(BufferBuilderPolygonView polygon, long[] vertexPointers, int stride, int vertexAmount) {
		ByteBufferBuilderAccessor bbba = (ByteBufferBuilderAccessor)allocator;
		long begin = bbba.testmod$getPointer();
		long capacity = bbba.testmod$getCapacity();
		
		polygon.setup(vertexPointers, stride, vertexAmount);
		
		if(!ExampleMod.DEBUG)
			return;
		
		LogUtils.getLogger().info("testmod$fillExtendedData: this = "+this+"; allocator = "+allocator+"; begin = "+Long.toHexString(begin));
		int midTexOffset = this.offsetsByElementId[IrisVertexFormats.MID_TEXTURE_ELEMENT.id()];
		int normalOffset = this.offsetsByElementId[VertexFormatElement.NORMAL.id()];
		int tangentOffset = this.offsetsByElementId[IrisVertexFormats.TANGENT_ELEMENT.id()];
		
		String extraData = "vertexAmount = "+vertexAmount+"; realVerticesAmount = "+this.vertexCount+"; vertexPointers = ["+Long.toHexString(vertexPointers[0])+"; "+Long.toHexString(vertexPointers[1])+"; "+Long.toHexString(vertexPointers[2])+"; "+Long.toHexString(vertexPointers[3])+"; "+"]; midTexOffset = "+midTexOffset+"; tangentOffset = "+tangentOffset+"; normalOffset = "+normalOffset;
		
		if (vertexAmount == 3) {
			for (int vertex = 0; vertex < vertexAmount; vertex++) {
				assertAddress(vertexPointers[vertex] + midTexOffset, "<type = Triangle 1; "+extraData+">");
				assertAddress(vertexPointers[vertex] + midTexOffset + 4, "<type = Triangle 2; "+extraData+">");
				assertAddress(vertexPointers[vertex] + tangentOffset, "<type = Triangle 3; "+extraData+">");
			}
		} else {
			for (int vertex = 0; vertex < vertexAmount; vertex++) {
				assertAddress(vertexPointers[vertex] + midTexOffset, "<type = Polygon 1; "+extraData+">");
				assertAddress(vertexPointers[vertex] + midTexOffset + 4, "<type = Polygon 2; "+extraData+">");
				assertAddress(vertexPointers[vertex] + normalOffset, "<type = Polygon 3; "+extraData+">");
				assertAddress(vertexPointers[vertex] + tangentOffset, "<type = Polygon 4; "+extraData+">");
			}
		}
		
	}
	
	@Unique
	private void assertAddress(long addr, String message) {
		ByteBufferBuilderAccessor bbba = (ByteBufferBuilderAccessor)allocator;
		long begin = bbba.testmod$getPointer();
		long capacity = bbba.testmod$getCapacity();
		if(addr < begin || addr >= begin + capacity)
			throw new IllegalStateException("EXCEPTION_ACCESS_VIOLATION: begin = "+Long.toHexString(begin)+"; addr = "+Long.toHexString(addr)+"; end = "+Long.toHexString(begin+capacity)+"; capacity = "+capacity+"; message = "+message);
	}
	
	
}
