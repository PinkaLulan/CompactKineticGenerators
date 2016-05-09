package com.lulan.compactkineticgenerators.item;

import ic2.api.item.IKineticRotor;
import ic2.core.block.kineticgenerator.gui.GuiWaterKineticGenerator;
import ic2.core.block.kineticgenerator.gui.GuiWindKineticGenerator;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.lulan.compactkineticgenerators.reference.Reference;

public class IridiumRotor extends BasicCkgItem implements IKineticRotor {

	protected static final ResourceLocation tx = new ResourceLocation(Reference.TEXTURES_ITEMS+"IridiumRotorTx.png");;
	protected static int maxWindStrength;
	protected static int minWindStrength;
	protected static int radius;
	protected static float efficiency;
	protected static boolean water;
	
	
	public IridiumRotor() {
		super();
		setUnlocalizedName("IridiumRotor");
		setMaxStackSize(1);
		maxWindStrength = 130;
		minWindStrength = 23;
		radius = 13;
		efficiency = 1F;
		water = true;
	}
	
	@Override
	public boolean isDamageable()
	{
		return false;
	}
	
	@Override
	public int getDiameter(ItemStack stack) {
		return radius;
	}

	@Override
	public ResourceLocation getRotorRenderTexture(ItemStack stack) {
		return tx;
	}

	@Override
	public float getEfficiency(ItemStack stack) {
		return efficiency;
	}

	@Override
	public int getMinWindStrength(ItemStack stack) {
		return minWindStrength;
	}

	@Override
	public int getMaxWindStrength(ItemStack stack) {
		return maxWindStrength;
	}

	@Override
	public boolean isAcceptedType(ItemStack stack, GearboxType type) {
		return true;
	}
	
	public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b)
	{
		info.add(StatCollector.translateToLocalFormatted("ic2.itemrotor.wind.info", new Object[] { Integer.valueOf(this.minWindStrength), Integer.valueOf(this.maxWindStrength) }));
		
		IKineticRotor.GearboxType type = null;
		
		if (Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().currentScreen instanceof GuiWaterKineticGenerator)
		{
			type = IKineticRotor.GearboxType.WATER;
		}
		else if ((Minecraft.getMinecraft().currentScreen != null) && ((Minecraft.getMinecraft().currentScreen instanceof GuiWindKineticGenerator)))
		{
			type = IKineticRotor.GearboxType.WIND;
		}
		
		if (type != null)
		{
			info.add(StatCollector.translateToLocal("ic2.itemrotor.fitsin." + isAcceptedType(itemStack, type)));
		}
	}

}
