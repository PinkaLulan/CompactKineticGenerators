package com.lulan.compactkineticgenerators.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lulan.compactkineticgenerators.init.ModBlocks;
import com.lulan.compactkineticgenerators.reference.Reference;

//建立mod的creative tab
public class CreativeTabCkg
{
		
	public static final CreativeTabs CKG_TAB = new CreativeTabs(Reference.MOD_ID)
	{
		//tab的icon
		@Override
		public Item getTabIconItem() {
			return new ItemStack(ModBlocks.BlockCkg, 1, 3).getItem();
		}
		
		//tab顯示的名稱會自動找語系檔的字串填入  不必使用getTranslatedTabLabel
		//只要在語系檔中加入  itemGroup.MOD名稱=要顯示的tab名稱  即可
	};

	

}

