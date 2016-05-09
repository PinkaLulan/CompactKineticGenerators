package com.lulan.compactkineticgenerators.proxy;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CommonProxy implements IProxy {
	
	//simple network
	public static final String channelNameS = "ckgSync";
	
	public static SimpleNetworkWrapper channelS;
	
	@Override
	public void registerChannel() {
//		//SimpleNetworkWrapper packets
//		channelS = NetworkRegistry.INSTANCE.newSimpleChannel(channelNameS);
//		
//		//register packets
//		channelE.registerMessage(S2CEntitySync.Handler.class, S2CEntitySync.class, ID.Packets.S2C_EntitySync, Side.CLIENT);
//		channelG.registerMessage(C2SGUIPackets.Handler.class, C2SGUIPackets.class, ID.Packets.C2S_GUIInput, Side.SERVER);
//		
	}
	
	

}
