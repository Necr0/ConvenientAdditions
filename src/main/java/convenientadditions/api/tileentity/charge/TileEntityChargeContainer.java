package convenientadditions.api.tileentity.charge;

import conveniencecore.util.MathHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

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
		return new SPacketUpdateTileEntity(this.pos, 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public int getContainedCharge(EnumFacing f) {
		return containedCharge;
	}

	@Override
	public int getChargeCapacity(EnumFacing f) {
		return chargeCapacity;
	}

	@Override
	public boolean isChargable(EnumFacing f) {
		return true;
	}

	@Override
	public void setContainedCharge(EnumFacing f, int amount) {
		if(!worldObj.isRemote){
			this.containedCharge=amount;
			this.markDirty();
		}
		IBlockState state=worldObj.getBlockState(pos);
		this.worldObj.notifyBlockUpdate(pos, state, state, 3);
	}

	@Override
	public int drainCharge(EnumFacing f, int amount) {
		int ret=MathHelper.drain(containedCharge, amount);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge-ret;
			this.markDirty();
		}
		IBlockState state=worldObj.getBlockState(pos);
		this.worldObj.notifyBlockUpdate(pos, state, state, 3);
    	
		return ret;
	}

	@Override
	public int fillCharge(EnumFacing f, int amount) {
		int ret=MathHelper.overflow(containedCharge, amount, chargeCapacity);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge+amount-ret;
			this.markDirty();
		}
		IBlockState state=worldObj.getBlockState(pos);
		this.worldObj.notifyBlockUpdate(pos, state, state, 3);
    	
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
		IBlockState state=worldObj.getBlockState(pos);
		this.worldObj.notifyBlockUpdate(pos, state, state, 3);
	}

	@Override
	public int drainCharge(int amount) {
		int ret=MathHelper.drain(containedCharge, amount);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge-ret;
			this.markDirty();
		}
		IBlockState state=worldObj.getBlockState(pos);
		this.worldObj.notifyBlockUpdate(pos, state, state, 3);
    	
		return ret;
	}

	@Override
	public int fillCharge(int amount) {
		int ret=MathHelper.overflow(containedCharge, amount, chargeCapacity);
		if(!worldObj.isRemote){
			this.containedCharge=containedCharge+amount-ret;
			this.markDirty();
		}
		IBlockState state=worldObj.getBlockState(pos);
		this.worldObj.notifyBlockUpdate(pos, state, state, 3);
    	
		return ret;
	}

	@Override
	public double getChargePercentage(EnumFacing f) {
		return MathHelper.percentage(containedCharge, chargeCapacity);
	}

	@Override
	public double getChargePercentage() {
		return MathHelper.percentage(containedCharge, chargeCapacity);
	}
}
