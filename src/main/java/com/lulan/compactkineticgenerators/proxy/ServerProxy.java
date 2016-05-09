package com.lulan.compactkineticgenerators.proxy;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;


public class ServerProxy extends CommonProxy {

	
	public static MinecraftServer getServer() {
		return MinecraftServer.getServer();
	}
	
	public static WorldServer[] getServerWorld() {
		return MinecraftServer.getServer().worldServers;
	}
	
	public static WorldServer getServerWorldByWorldID(int worldID) {
		WorldServer[] worlds = getServerWorld();
				
		for(WorldServer getw : worlds) {
			if(getw.provider.dimensionId == worldID) {
				return getw;
			}
		}
		return null;
	}
	
	
}
