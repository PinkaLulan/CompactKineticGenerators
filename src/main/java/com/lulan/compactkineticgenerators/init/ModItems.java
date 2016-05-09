package com.lulan.compactkineticgenerators.init;

import com.lulan.compactkineticgenerators.item.BasicCkgItem;
import com.lulan.compactkineticgenerators.item.IridiumBlade;
import com.lulan.compactkineticgenerators.item.IridiumRotor;
import com.lulan.compactkineticgenerators.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)	//登錄object holder使mod的物件容易流通 其他人可以直接讀取該物件
public class ModItems {

	//items
	public static final BasicCkgItem IridiumBlade = new IridiumBlade();
	public static final BasicCkgItem IridiumRotor = new IridiumRotor();

	//登錄item到遊戲中 (在pre init階段登錄)
	public static void init() {
		//register items
		GameRegistry.registerItem(IridiumBlade, "IridiumBlade");
		GameRegistry.registerItem(IridiumRotor, "IridiumRotor");
		
	}
	
}
