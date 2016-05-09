package com.lulan.compactkineticgenerators.tileentity;

import ic2.core.block.kineticgenerator.tileentity.TileEntityWindKineticGenerator;
import net.minecraft.nbt.NBTTagCompound;

import com.lulan.compactkineticgenerators.handler.ConfigHandler;

abstract public class BasicTileCkwm extends TileEntityWindKineticGenerator {
	
	public int type, tick;
	protected float scaleOutput;
	protected float scaleDamage;
	
	
	public BasicTileCkwm()
	{
		this(0);
	}
	
	public BasicTileCkwm(int type)
	{
		super();
		this.type = type;
		this.tick = 0;
		
		//setup scale
		try
		{
			this.scaleOutput = (float) ConfigHandler.scaleOutput[this.type];
			this.scaleDamage = (float) ConfigHandler.scaleDamage[this.type];
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.scaleOutput = 1F;
			this.scaleDamage = 1F;
		}
		
	}

	@Override
	protected void updateEntityServer()
	{
		super.updateEntityServer();
		
		//rotor take more damage
		if (this.tick++ % getTickRate() == 0)
		{
			if (getKuOutput() > 0)
			{
				double wind = calcWindStrength();

				if (wind >= getMinWindStrength())
				{
					if (wind <= getMaxWindStrength())
					{
						this.rotorSlot.damage((int) (1 * this.scaleDamage), false);
					}
					else
					{
						this.rotorSlot.damage((int) (4 * this.scaleDamage), false);
					}
				}
			}
		}
	}
	
	@Override
	public int getKuOutput()
	{
		return (int) (scaleOutput * super.getKuOutput());
	}
	
	//讀取nbt資料
	@Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);	//從nbt讀取方塊的xyz座標
        
        type = nbt.getInteger("type");
        scaleOutput = nbt.getFloat("scale");
        scaleDamage = nbt.getFloat("damage");
    }
	
	//將資料寫進nbt
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setInteger("type", type);
		nbt.setFloat("scale", scaleOutput);
		nbt.setFloat("damage", scaleDamage);
	}
	
	
}
