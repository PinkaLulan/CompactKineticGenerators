package com.lulan.compactkineticgenerators.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

	
	public static World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	
	//client player
	public static EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	//client minecraft
	public static GameSettings getGameSetting() {
		return Minecraft.getMinecraft().gameSettings;
	}
	
	//client minecraft
	public static Minecraft getMineraft() {
		return Minecraft.getMinecraft();
	}
		
		
		
}
