package com.lulan.compactkineticgenerators.init;

import net.minecraft.block.Block;

import com.lulan.compactkineticgenerators.block.BlockCkg;
import com.lulan.compactkineticgenerators.block.ItemBlockCkg;
import com.lulan.compactkineticgenerators.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)	//登錄object holder使mod的物件容易流通 其他人可以直接讀取該物件
public class ModBlocks {

	public static final Block BlockCkg = new BlockCkg();

	
	public static void init() {
		GameRegistry.registerBlock(BlockCkg, ItemBlockCkg.class, "BlockCkg");
		
	}
	
	
}

