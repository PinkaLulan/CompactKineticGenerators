package com.lulan.compactkineticgenerators.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

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


}
