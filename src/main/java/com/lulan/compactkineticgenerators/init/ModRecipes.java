package com.lulan.compactkineticgenerators.init;

import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;


public class ModRecipes {
	//配方範例
	//GameRegistry.addSmelting(取得物, 材料, 0.1f經驗);
	//GameRegistry.addRecipe(new ShapedOreRecipe(取得物, " s ", "sss", " s ", 's', "stickWood" 材料陣列));
    //GameRegistry.addRecipe(new ShapelessOreRecipe(取得物, 材料A, 材料B, ...));		
	public static void init() {
		//配方材料or成品
		ItemStack refironCasing = IC2Items.getItem("casingadviron");
		ItemStack refironShaft = IC2Items.getItem("steelshaft");
		ItemStack advAlloy = IC2Items.getItem("advancedAlloy");
		ItemStack engCrystal = IC2Items.getItem("energyCrystal");
		engCrystal.setItemDamage(OreDictionary.WILDCARD_VALUE);
		ItemStack lapCrystal = IC2Items.getItem("lapotronCrystal");
		lapCrystal.setItemDamage(OreDictionary.WILDCARD_VALUE);
		ItemStack mfe = IC2Items.getItem("mfeUnit");
		ItemStack mfsu = IC2Items.getItem("mfsUnit");
		ItemStack kg = IC2Items.getItem("kineticGenerator");
		ItemStack iriOre = IC2Items.getItem("iridiumOre");
		ItemStack iriPlate = IC2Items.getItem("iridiumPlate");
		ItemStack iriBlade = new ItemStack(ModItems.IridiumBlade);
		ItemStack iriRotor = new ItemStack(ModItems.IridiumRotor);
		ItemStack kwm = IC2Items.getItem("WindKineticGenerator");
		ItemStack kwa = IC2Items.getItem("WaterKineticGenerator");
		ItemStack lvt = IC2Items.getItem("lvTransformer");
		ItemStack mvt = IC2Items.getItem("mvTransformer");
		ItemStack hvt = IC2Items.getItem("hvTransformer");
		ItemStack evt = IC2Items.getItem("evTransformer");
		ItemStack lvwm = new ItemStack(ModBlocks.BlockCkg, 1, 0);
		ItemStack mvwm = new ItemStack(ModBlocks.BlockCkg, 1, 1);
		ItemStack hvwm = new ItemStack(ModBlocks.BlockCkg, 1, 2);
		ItemStack evwm = new ItemStack(ModBlocks.BlockCkg, 1, 3);
		ItemStack lvwa = new ItemStack(ModBlocks.BlockCkg, 1, 4);
		ItemStack mvwa = new ItemStack(ModBlocks.BlockCkg, 1, 5);
		ItemStack hvwa = new ItemStack(ModBlocks.BlockCkg, 1, 6);
		ItemStack evwa = new ItemStack(ModBlocks.BlockCkg, 1, 7);
		ItemStack lvg = new ItemStack(ModBlocks.BlockCkg, 1, 8);
		ItemStack mvg = new ItemStack(ModBlocks.BlockCkg, 1, 9);
		ItemStack hvg = new ItemStack(ModBlocks.BlockCkg, 1, 10);
		ItemStack evg = new ItemStack(ModBlocks.BlockCkg, 1, 11);
		
		//SHAPELESS RECIPE
//		GameRegistry.addRecipe(new ShapelessOreRecipe(waypoint, grudeStack, Items.stick));
		
		//SHAPED RECIPE
		//rotor
		GameRegistry.addRecipe(new ShapedOreRecipe(iriBlade,"pop","pop","pop",'p',iriPlate,'o',iriOre));
		GameRegistry.addRecipe(new ShapedOreRecipe(iriRotor," b ","bbb"," b ",'b',iriBlade));
		
		//wind
		GameRegistry.addRecipe(new ShapedOreRecipe(lvwm," w ","wtw"," w ",'w',kwm,'t',lvt));
		GameRegistry.addRecipe(new ShapedOreRecipe(mvwm," w ","wtw"," w ",'w',lvwm,'t',mvt));
		GameRegistry.addRecipe(new ShapedOreRecipe(hvwm," w ","wtw"," w ",'w',mvwm,'t',hvt));
		GameRegistry.addRecipe(new ShapedOreRecipe(evwm," w ","wtw"," w ",'w',hvwm,'t',evt));
		
		//water
		GameRegistry.addRecipe(new ShapedOreRecipe(lvwa," w ","wtw"," w ",'w',kwa,'t',lvt));
		GameRegistry.addRecipe(new ShapedOreRecipe(mvwa," w ","wtw"," w ",'w',lvwa,'t',mvt));
		GameRegistry.addRecipe(new ShapedOreRecipe(hvwa," w ","wtw"," w ",'w',mvwa,'t',hvt));
		GameRegistry.addRecipe(new ShapedOreRecipe(evwa," w ","wtw"," w ",'w',hvwa,'t',evt));
		
		//generator
		GameRegistry.addRecipe(new ShapedOreRecipe(lvg,"ccc","gms","ccc",'c',refironCasing,'g',engCrystal,'m',kg,'s',refironShaft));
		GameRegistry.addRecipe(new ShapedOreRecipe(mvg,"ccc","gms","ccc",'c',refironCasing,'g',mfe,'m',lvg,'s',refironShaft));
		GameRegistry.addRecipe(new ShapedOreRecipe(hvg,"ccc","gms","ccc",'c',advAlloy,'g',lapCrystal,'m',mvg,'s',advAlloy));
		GameRegistry.addRecipe(new ShapedOreRecipe(evg,"ccc","gms","ccc",'c',iriPlate,'g',mfsu,'m',hvg,'s',iriPlate));
		
	}

	
}

