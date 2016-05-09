package com.lulan.compactkineticgenerators.init;

import com.lulan.compactkineticgenerators.tileentity.TileCkwmE;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmH;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmL;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmM;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileentity {
	
	public static void init() {
		//tile entity
		GameRegistry.registerTileEntity(TileCkwmL.class, "TileEntityCkwmL");
		GameRegistry.registerTileEntity(TileCkwmM.class, "TileEntityCkwmM");
		GameRegistry.registerTileEntity(TileCkwmH.class, "TileEntityCkwmH");
		GameRegistry.registerTileEntity(TileCkwmE.class, "TileEntityCkwmE");

	}

	
}

