package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExampleMod implements ModInitializer {
	
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");
    public static final boolean DEBUG = false;
    
    public static final CustomBlock CUSTOM_BLOCK = Registry.register(
            Registries.BLOCK,
            Identifier.of("modid", "custom_block"),
            new CustomBlock(AbstractBlock.Settings.create())
    );
    
    public static final Item CUSTOM_BLOCK_ITEM = Registry.register(
            Registries.ITEM,
            Identifier.of("modid", "custom_block"),
            new BlockItem(CUSTOM_BLOCK, new Item.Settings())
    );
    
    public static final BlockEntityType<CustomBlockEntity> CUSTOM_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of("modid", "custom_block_entity"),
            BlockEntityType.Builder.create(CustomBlockEntity::new, CUSTOM_BLOCK).build()
    );

	@Override
	public void onInitialize() {
		
		LOGGER.info("Hello Fabric world!");
	}
}