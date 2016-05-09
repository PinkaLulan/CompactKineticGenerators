package com.lulan.compactkineticgenerators.init;

import com.lulan.compactkineticgenerators.tileentity.TileCkgE;
import com.lulan.compactkineticgenerators.tileentity.TileCkgH;
import com.lulan.compactkineticgenerators.tileentity.TileCkgL;
import com.lulan.compactkineticgenerators.tileentity.TileCkgM;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmE;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmH;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmL;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmM;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileentity {
	
	public static void init() {
		//wind mills
		GameRegistry.registerTileEntity(TileCkwmL.class, "TileEntityCkwmL");
		GameRegistry.registerTileEntity(TileCkwmM.class, "TileEntityCkwmM");
		GameRegistry.registerTileEntity(TileCkwmH.class, "TileEntityCkwmH");
		GameRegistry.registerTileEntity(TileCkwmE.class, "TileEntityCkwmE");
		//water mills
//		GameRegistry.registerTileEntity(TileCkwaL.class, "TileEntityCkwaL");
//		GameRegistry.registerTileEntity(TileCkwaM.class, "TileEntityCkwaM");
//		GameRegistry.registerTileEntity(TileCkwaH.class, "TileEntityCkwaH");
//		GameRegistry.registerTileEntity(TileCkwaE.class, "TileEntityCkwaE");
		//wind mills
		GameRegistry.registerTileEntity(TileCkgL.class, "TileEntityCkgL");
		GameRegistry.registerTileEntity(TileCkgM.class, "TileEntityCkgM");
		GameRegistry.registerTileEntity(TileCkgH.class, "TileEntityCkgH");
		GameRegistry.registerTileEntity(TileCkgE.class, "TileEntityCkgE");
	}

	
}

