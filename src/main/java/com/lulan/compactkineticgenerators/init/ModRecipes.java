package com.lulan.compactkineticgenerators.init;

import ic2.api.item.IC2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;


public class ModRecipes {
	//配方範例
	//GameRegistry.addSmelting(取得物, 材料, 0.1f經驗);
	//GameRegistry.addRecipe(new ShapedOreRecipe(取得物, " s ", "sss", " s ", 's', "stickWood" 材料陣列));
    //GameRegistry.addRecipe(new ShapelessOreRecipe(取得物, 材料A, 材料B, ...));		
	public static void init() {
		//配方材料or成品
		ItemStack iriOre = IC2Items.getItem("iridiumOre");
		ItemStack iriPlate = IC2Items.getItem("iridiumPlate");
		ItemStack iriBlade = new ItemStack(ModItems.IridiumBlade);
		ItemStack iriRotor = new ItemStack(ModItems.IridiumRotor);
		ItemStack kwm = IC2Items.getItem("WindKineticGenerator");
		ItemStack lvt = IC2Items.getItem("lvTransformer");
		ItemStack mvt = IC2Items.getItem("mvTransformer");
		ItemStack hvt = IC2Items.getItem("hvTransformer");
		ItemStack evt = IC2Items.getItem("evTransformer");
		ItemStack lvwm = new ItemStack(ModBlocks.BlockCkwm, 1, 0);
		ItemStack mvwm = new ItemStack(ModBlocks.BlockCkwm, 1, 1);
		ItemStack hvwm = new ItemStack(ModBlocks.BlockCkwm, 1, 2);
		ItemStack evwm = new ItemStack(ModBlocks.BlockCkwm, 1, 3);
		
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
		
	}

	
}

