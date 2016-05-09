package com.lulan.compactkineticgenerators.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.lulan.compactkineticgenerators.creativetab.CreativeTabCkg;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

abstract public class BasicCkgContainer extends BlockContainer {

	//指定方塊類型block
	public BasicCkgContainer(Material material) {
		super(material);
		this.setCreativeTab(CreativeTabCkg.CKG_TAB);	//加入到creative tab中
	}
	
	//無指定類型時 預設為rock型
	public BasicCkgContainer() {
		this(Material.rock);
		this.setCreativeTab(CreativeTabCkg.CKG_TAB);	//加入到creative tab中
	}
	
	//name設定用方法: 將原本mc給的block名稱 去掉.之前的字串 以便另外串上mod名稱形成的字串
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
	}
	
	//將name冠上mod名稱 用於之後給各語系檔案放上正確名稱
	//格式為tile.MOD名稱:方塊名稱.name
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.ckg:%s", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	//方塊圖示登錄
	//取出方塊名稱(不含mod名稱)作為參數丟給icon register來登錄icon
	//注意icon只在client端才需要執行
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}

	@Override
	abstract public TileEntity createNewTileEntity(World world, int meta);

}

