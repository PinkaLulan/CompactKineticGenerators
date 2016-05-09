package com.lulan.compactkineticgenerators.tileentity;

import ic2.api.energy.tile.IKineticSource;
import ic2.core.IC2;
import ic2.core.block.generator.tileentity.TileEntityKineticGenerator;
import ic2.core.init.MainConfig;
import ic2.core.util.ConfigUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import com.lulan.compactkineticgenerators.handler.ConfigHandler;
import com.lulan.compactkineticgenerators.utility.LogHelper;

public class BasicTileCkg extends TileEntityKineticGenerator {

	public int type;
	protected double guiproduction = 0.0D;
	protected double production = 0.0D;
	protected int receivedkinetic = 0;
	protected final int MaxStorage;
	protected final double productionpeerkineticunit;

	
	public BasicTileCkg()
	{
		this(0);
	}
	
	public BasicTileCkg(int type)
	{
		super();
		this.type = type;
		
		this.productionpeerkineticunit = (0.25D * ConfigUtil.getFloat(MainConfig.get(), "balance/energy/generator/Kinetic"));
		this.updateTicker = IC2.random.nextInt(getTickRate());
		
		//EU storage
		switch (this.type)
		{
		case 1:
			this.MaxStorage = 4000000;
			break;
		case 2:
			this.MaxStorage = 10000000;
			break;
		case 3:
			this.MaxStorage = 40000000;
			break;
		default:
			this.MaxStorage = 1000000;
			break;
		}
		
	}
	
	@Override
	protected void updateEntityServer()
	{
		boolean newActive = gainEnergy();
		
		if (this.updateTicker++ >= getTickRate())
		{
			this.guiproduction = this.production;
			this.updateTicker = 0;
		}
		
		if (this.EUstorage > this.MaxStorage)
		{
			this.EUstorage = this.MaxStorage;
		}
		
		if (getActive() != newActive)
		{
			setActive(newActive);
		}
	}
	
	@Override
	protected boolean gainEnergy()
	{
		ForgeDirection dir = ForgeDirection.getOrientation(getFacing());
		
		TileEntity te = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
		
		if ((te instanceof IKineticSource))
		{
			//取得最大可獲得KU
			int kineticbandwith = ((IKineticSource)te).maxrequestkineticenergyTick(dir.getOpposite());
			
			double freeEU = this.MaxStorage - this.EUstorage;
			double gainEU = this.productionpeerkineticunit * kineticbandwith;
			
			//直接將剩餘KU全部領走
			this.receivedkinetic = ((IKineticSource)te).requestkineticenergy(dir.getOpposite(), kineticbandwith);
			
			if (this.receivedkinetic >= 0)
			{
				this.production = this.receivedkinetic * this.productionpeerkineticunit;
				this.EUstorage += this.production;
				return true;
			}
			
			//意義不明, 此段忽略
//			if (freeEU > 0D && freeEU <= gainEU)
//			{
//				freeEU = gainEU;
//			}
//			
//			if (freeEU >= gainEU)
//			{
//				this.receivedkinetic = ((IKineticSource)te).requestkineticenergy(dir.getOpposite(), kineticbandwith);
//				
//				if (this.receivedkinetic != 0)
//				{
//					this.production = this.receivedkinetic * this.productionpeerkineticunit;
//					this.EUstorage += this.production;
//					return true;
//				}
//			}
		}
		
		this.production = 0D;
		this.receivedkinetic = 0;
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.type = nbt.getInteger("type");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("type", this.type);
	}
	
	//4 = 2048, 5 = 8192, 6 = 32768, 7 = 131072
	@Override
	public int getSourceTier()
	{
		//custom type
		if (this.type >= 3)
		{
			return ConfigHandler.customKGTier;
		}
		else
		{
			return 4 + this.type;
		}
	}
	
	@Override
	public int gaugeEUStorageScaled(int i)
	{
		return (int)(this.EUstorage * i / this.MaxStorage);
	}
	
	@Override
	public String getInventoryName()
	{
		return StatCollector.translateToLocalFormatted("tile.ckg:BlockCkg"+(8+this.type)+".name");
	}
	
	@Override
	public double getproduction()
	{
		return this.guiproduction;
	}
	
	
}
