package convenientadditions.api.tileentity.charge;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.api.util.MathHelper;

public abstract class TileEntityChargeContainer extends TileEntity implements ISidedChargeContainer {

	public int containedCharge=0;
	public int chargeCapacity;
	
	public TileEntityChargeContainer(int chargeCapacity) {
		this.chargeCapacity=chargeCapacity;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("CHARGE"))
			this.containedCharge=nbt.getInteger("CHARGE");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("CHARGE",containedCharge);
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt=new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}

	@Override
	public int getContainedCharge(ForgeDirection f) {
		return containedCharge;
	}

	@Override
	public int getChargeCapacity(ForgeDirection f) {
		return chargeCapacity;
	}

	@Override
	public boolean isChargable(ForgeDirection f) {
		return true;
	}

	@Override
	public void setContainedCharge(ForgeDirection f, int amount) {
		if(!worldObj.isRemote){
			this.containedCharge=amount;
			this.markDirty();
		}
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public int drainCharge(ForgeDirection f, int amount) {
		int ret=MathHelper.drain(containedCharge, amount);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge-ret;
			this.markDirty();
		}
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	
		return ret;
	}

	@Override
	public int fillCharge(ForgeDirection f, int amount) {
		int ret=MathHelper.overflow(containedCharge, amount, chargeCapacity);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge+amount-ret;
			this.markDirty();
		}
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	
		return ret;
	}

	@Override
	public int getContainedCharge() {
		return containedCharge;
	}

	@Override
	public int getChargeCapacity() {
		return chargeCapacity;
	}

	@Override
	public boolean isChargable() {
		return true;
	}

	@Override
	public void setContainedCharge(int amount) {
		if(!worldObj.isRemote){
			this.containedCharge=amount;
			this.markDirty();
		}
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public int drainCharge(int amount) {
		int ret=MathHelper.drain(containedCharge, amount);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge-ret;
			this.markDirty();
		}
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	
		return ret;
	}

	@Override
	public int fillCharge(int amount) {
		int ret=MathHelper.overflow(containedCharge, amount, chargeCapacity);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge+amount-ret;
			this.markDirty();
		}
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	
		return ret;
	}

	@Override
	public double getChargePercentage(ForgeDirection f) {
		return MathHelper.percentage(containedCharge, chargeCapacity);
	}

	@Override
	public double getChargePercentage() {
		return MathHelper.percentage(containedCharge, chargeCapacity);
	}
}
