package com.lulan.compactkineticgenerators.block;

import ic2.api.tile.IWrenchable;
import ic2.core.IC2;
import ic2.core.IHasGui;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.lulan.compactkineticgenerators.reference.Reference;
import com.lulan.compactkineticgenerators.tileentity.BasicTileCkwm;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmE;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmH;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmL;
import com.lulan.compactkineticgenerators.tileentity.TileCkwmM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *  meta: 0:LV, 1:MV, 2:HV, 3:EV
 *
 */
public class BlockCkwm extends BasicCkgContainer
{
	//[face][side], iconID: 6:head, 7:tail, 8:side, 9:top, 10:btm  (0~5 save for vanilla icon method)
	private static final int[][] face2iconID = {{6,  7,  8,  8,  8,  8},  //face btm(0)
												{7,  6,  8,  8,  8,  8},  //face top(1)
												{10, 9,  6,  7,  8,  8},  //face N(2)
												{10, 9,  7,  6,  8,  8},  //face S(3)
												{10, 9,  8,  8,  6,  7},  //face W(4)
												{10, 9,  8,  8,  7,  6}}; //face E(5)
	IIcon[] icons = new IIcon[14];
	
	
	public BlockCkwm()
	{
		super(Material.iron);
		this.setBlockName("BlockCkwm");
		this.setStepSound(soundTypeMetal);
	    this.setHardness(3F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmLH"));
		icons[1] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmLT"));
		icons[2] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmLS"));
		icons[3] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmMH"));
		icons[4] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmMT"));
		icons[5] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmMS"));
		icons[6] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmHH"));
		icons[7] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmHT"));
		icons[8] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmHS"));
		icons[9] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmEH"));
		icons[10] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmET"));
		icons[11] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmES"));
		icons[12] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmT"));
		icons[13] = iconRegister.registerIcon(String.format(Reference.MOD_ID+":BlockCkwmB"));
	}
	
	//side: 0:bottom, 1:top, 2:N, 3:S, 4:W, 5:E
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
	{
		switch (side)
		{
		case 0:  //default top
			return icons[12];
		case 1:  //default btm
			return icons[13];
		case 2:  //default tail
			return icons[meta * 3 + 1];
		case 3:  //default head
			return icons[meta * 3];
		case 4:  //default side
			return icons[meta * 3 + 2];
		case 5:  //default side
			return icons[meta * 3 + 2];
		case 6:  //head
			return icons[meta * 3];
		case 7:  //tail
			return icons[meta * 3 + 1];
		case 8:  //side
			return icons[meta * 3 + 2];
		case 9:  //top
			return icons[12];
		default: //btm
			return icons[13];
		}
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
		TileEntity tile = block.getTileEntity(x, y, z);
		
		if (tile instanceof BasicTileCkwm)
		{
			return this.getIcon(face2iconID[((BasicTileCkwm) tile).getFacing()][side], block.getBlockMetadata(x, y, z));
		}
		
		return this.getIcon(face2iconID[2][side], 0);
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		switch (meta)
		{
		case 1:
			return new TileCkwmM();
		case 2:
			return new TileCkwmH();
		case 3:
			return new TileCkwmE();
		default:
			return new TileCkwmL();
		}
	}
	
	/**右鍵點到方塊時呼叫此方法
	 * 參數: world,方塊x,y,z,玩家,玩家面向,玩家點到的x,y,z
	 */	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
//		if (world.isRemote)
//		{	//client端只需要收到true
//    		return true;
//    	}
//		else if (!player.isSneaking())
//		{	//server端: 按住shift不能點開方塊gui
//			TileEntity tile = world.getTileEntity(x, y, z);
//    		
//    		if (tile != null) {	//開啟方塊GUI 參數:玩家,mod instance,gui ID,world,座標xyz
//    			FMLNetworkHandler.openGui(player, CompactKineticWindmills.instance, ID.G.WINDMILL, world, x, y, z);
//    		}
//
//    		return true;
//    	}
//    	return false;
		
		//client side
		if (world.isRemote)
		{
			return true;
		}
		//server side
		else if (!player.isSneaking())
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			
			if (tile instanceof IHasGui)
			{
				if (!world.isRemote)
				{
					return IC2.platform.launchGui(player, (IHasGui)tile);
				}
				return true;
			}
		}

		return false;
    }
	
	@Override
    public int damageDropped(int meta)
    {
        return meta;
    }
	
	//打掉方塊後, 掉落其內容物
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
	
		//抓到tile entity後, 掃描全部slot內容物, 然後做成entity掉落出來
		if (tile instanceof ISidedInventory)
		{
			ISidedInventory tile2 = (ISidedInventory) tile;
			//for all slots
			for (int i = 0; i < tile2.getSizeInventory(); i++)
			{
				ItemStack itemstack = tile2.getStackInSlot(i);
	
				if (itemstack != null)
				{
					float f = world.rand.nextFloat() * 0.8F + 0.1F;  //設定要隨機噴出的range
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
	
					while (itemstack.stackSize > 0)
					{
						//如果物品超過一個隨機數量, 會分更多疊噴出
						int j = world.rand.nextInt(32) + 32;
						if (j > itemstack.stackSize)
						{  
							j = itemstack.stackSize;
						}
						itemstack.stackSize -= j;
						
						//將item做成entity, 生成到世界上
						EntityItem item = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
						
						//如果有NBT tag, 也要複製到物品上
						if(itemstack.hasTagCompound()) {
							item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
	
						world.spawnEntityInWorld(item);	//生成entity
					}
				}
			}
			
			world.func_147453_f(x, y, z, block);	//alert block changed
		}
		
		//呼叫原先的breakBlock, 會把tile entity移除掉
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs ctab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
        list.add(new ItemStack(item, 1, 3));
    }
	
	//set facing when placed
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
	    TileEntity tile = world.getTileEntity(x, y, z);
	    
	    if ((tile instanceof IWrenchable) && itemStack != null)
	    {
	    	IWrenchable tile2 = (IWrenchable) tile;
	    	
	    	//set meta value
	    	int meta = itemStack.getItemDamage();
	    	if (meta > 3) meta = 0;
	    	
	    	world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	    	
	    	//set facing
	    	if (entity != null)
	    	{
	    		int face = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
	    		
	    		switch (face)
	    		{
	    		case 0: 
	    			tile2.setFacing((short)2);  //N
	    			break;
	    		case 1: 
	    			tile2.setFacing((short)5);  //E
	    			break;
	    		case 2: 
	    			tile2.setFacing((short)3);  //S
	    			break;
	    		case 3: 
	    			tile2.setFacing((short)4);  //W
	    			break;
		        }
	    	}
	    	else
	    	{
	    		//若非entity放置方塊, 則預設朝向北
	    		tile2.setFacing((short) 2);
	    	}
		}
	}
	

}
