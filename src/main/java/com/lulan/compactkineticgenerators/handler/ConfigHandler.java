package com.lulan.compactkineticgenerators.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.lulan.compactkineticgenerators.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class ConfigHandler
{
	
	public static Configuration config;		//宣告config檔實體
	
	//GENERAL
	public static boolean debugMode = false;
	
	public static int customKGTier = 7;
	
	public static Property propOutput, propDamage, propOutputWater, propDamageWater;
	public static double[] scaleOutput = new double[] {4D, 16D, 64D, 256D};
	public static double[] scaleDamage = new double[] {4D, 16D, 64D, 256D};
	public static double[] scaleOutputWater = new double[] {4D, 16D, 64D, 256D};
	public static double[] scaleDamageWater = new double[] {4D, 16D, 64D, 256D};
	
	
	//讀取設定檔參數
	private static void loadConfiguration()
	{

		//是否開啟debug mode
		debugMode = config.getBoolean("Debug Mode", "general", false, "Enable debug message (SPAM WARNING)");
		
		customKGTier = config.getInt("ExtremeKG Tier", "general", 7, 7, 13, "Extreme Kinetic Generator energy tier, default 7 = max 131,072 EU/t");
		
		propOutput = config.get("general", "Wind Output Scale", scaleOutput, "output scale vs classic wind generator");
		propDamage = config.get("general", "Wind Rotor Damage Scale", scaleOutput, "rotor damage scale vs classic wind generator");
		scaleOutput = getDoubleArrayFromConfig(scaleOutput, propOutput);
		scaleDamage = getDoubleArrayFromConfig(scaleDamage, propDamage);

		//若設定檔有更新過, 則儲存
		if (config.hasChanged())
		{
			config.save();
		}
	}
	
	//check get value
	public static int[] getIntArraySpecialLength(int[] defaultValue, Property target, int type)
	{
		int[] geti = target.getIntList();
		
		switch (type)
		{
		case 0:
			if(geti != null && geti.length % 10 == 0) {
				return geti;
			}
			else {
				target.set(defaultValue);
				return defaultValue;
			}
		}
		
		return geti;
	}
	
	//check get value
	public static int[] getIntArrayFromConfig(int[] defaultValue, Property target)
	{
		int size = defaultValue.length;
		int[] geti = target.getIntList();
		
		if (geti != null && geti.length == size)
		{
			return geti;
		}
		else
		{
			target.set(defaultValue);
			return defaultValue;
		}
	}
	
	//check get value
	public static double[] getDoubleArrayFromConfig(double[] defaultValue, Property target)
	{
		int size = defaultValue.length;
		double[] getd = target.getDoubleList();
		
		if (getd != null && getd.length == size)
		{
			return getd;
		}
		else
		{
			target.set(defaultValue);
			return defaultValue;
		}
	}
	
	//check get value
	public static boolean[] getBooleanArrayFromConfig(boolean[] defaultValue, Property target)
	{
		int size = defaultValue.length;
		boolean[] getd = target.getBooleanList();
		
		if (getd != null && getd.length == size)
		{
			return getd;
		}
		else
		{
			target.set(defaultValue);
			return defaultValue;
		}
	}
	
	//設定檔處理 初始化動作
	public static void init(File configFile)
	{		
		//如果設定檔實體還未建立 則建立之
		if (config == null)
		{
			config = new Configuration(configFile);	//建立config檔實體
			loadConfiguration();
		}
	}
	
	//若版本更新後 設定檔需要更新 則在此區塊增加更新方法
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		//若設定檔的mod id跟目前mod id不同時 則進行更新
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}

}
