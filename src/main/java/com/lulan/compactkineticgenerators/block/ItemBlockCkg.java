package com.lulan.compactkineticgenerators.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.lulan.compactkineticgenerators.handler.ConfigHandler;

public class ItemBlockCkg extends ItemBlockWithMetadata
{
	
	public ItemBlockCkg(Block b1)
	{
		super(b1, b1);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
    {
		if (itemstack != null && itemstack.getItemDamage() > 0)
		{
			return this.field_150939_a.getUnlocalizedName() + itemstack.getItemDamage();
		}
		
		return this.field_150939_a.getUnlocalizedName();
    }

	//display egg information
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
    	if (itemstack != null)
    	{
    		int meta = itemstack.getItemDamage();
    		
    		//wind mill
    		if (meta <= 3)
    		{
    			list.add(StatCollector.translateToLocalFormatted("tooltip.ckg.ku", ConfigHandler.scaleOutput[meta]));
    		}
    		//water mill
    		else if (meta <= 7)
    		{
    			list.add(StatCollector.translateToLocalFormatted("tooltip.ckg.ku", ConfigHandler.scaleOutputWater[meta - 4]));
    		}
    		//kg L/M/H
    		else if (meta <= 10)
    		{
    			int eu = 2048 << ((meta - 8) * 2);
    			list.add(StatCollector.translateToLocalFormatted("tooltip.ckg.eu", eu));
    			list.add(StatCollector.translateToLocalFormatted("tooltip.ckg.capacity"+(meta-8)));
    		}
    		//kg E
    		else
    		{
    			int eu = 2048 << ((ConfigHandler.customKGTier - 4) * 2);
    			list.add(StatCollector.translateToLocalFormatted("tooltip.ckg.eu", eu));
    			list.add(StatCollector.translateToLocalFormatted("tooltip.ckg.capacity"+(meta-8)));
    		}
    	}
    }
	

}
