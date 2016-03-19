package convenientadditions.tileentity.seedbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.api.tileentity.IConfigurable;
import convenientadditions.api.util.MathHelper;
import convenientadditions.entity.specialitem.CAEntitySpecialItem;

public class TileEntitySeedBox extends TileEntity implements ISidedInventory, IConfigurable {

	public HashMap<ForgeDirection, Boolean> outletSides=new HashMap<ForgeDirection, Boolean>();
	
	public TileEntitySeedBox() {
		super();
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			outletSides.put(f, (f!=ForgeDirection.DOWN?false:true));
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("OUTLET")){
			byte in=nbt.getByte("OUTLET");
			MathHelper.Bitmask mask=new MathHelper.Bitmask(in);
			for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
				outletSides.put(f,mask.getBit(f.ordinal()));
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		MathHelper.Bitmask mask=new MathHelper.Bitmask(0);
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			mask.setBit(f.ordinal(), outletSides.get(f));
		}
		nbt.setByte("OUTLET", (byte)mask.get());
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
	public boolean configureSide(ForgeDirection f) {
		this.outletSides.put(f, !outletSides.get(f));
		this.markDirty();
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return null;
	}

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        if (itemStack != null && itemStack.stackSize > 0)
        {
        	List<ForgeDirection> outputs=getValidOutputDirections();
        	if(outputs.size()>0){
        		ForgeDirection output=(ForgeDirection) outputs.toArray()[new Random().nextInt(outputs.size())];
	        	CAEntitySpecialItem item=new CAEntitySpecialItem(this.worldObj,this.xCoord+0.5+(output.offsetX*0.8),this.yCoord+0.5+(output.offsetY*0.8),this.zCoord+0.5+(output.offsetZ*0.8),itemStack);
	        	for(long b:SeedBoxItemBehaviourRegistry.getItemBehaviour(itemStack)){
	        		item.addBehaviour(b);
	        	}
	        	item.setVelocity(0d, 0d, 0d);
	        	item.delayBeforeCanPickup=20;
	            this.worldObj.spawnEntityInWorld(item);
	            item.updateBehaviours();
        	}
        }
    }
    
	@Override
	public String getInventoryName() {
		return "inventory."+ConvenientAdditionsMod.MODID+":"+Reference.seedBoxBlockName+".name";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return !isOutput(ForgeDirection.getOrientation(side))?new int[]{0}:new int[]{};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack,int side) {
		return !isOutput(ForgeDirection.getOrientation(side))&&getValidOutputDirections().size()>0;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}
	
	public boolean isOutput(ForgeDirection f){
		return this.outletSides.get(f);
	}
	
	public boolean canOutput(ForgeDirection f){
		int x=xCoord+f.offsetX,y=yCoord+f.offsetY,z=zCoord+f.offsetZ;
		return isOutput(f)&&!worldObj.getBlock(x,y,z).isBlockSolid(worldObj, x,y,z, f.getOpposite().ordinal());
	}
	
	public List<ForgeDirection> getValidOutputDirections(){
		ArrayList<ForgeDirection> ret=new ArrayList<ForgeDirection>();
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			if(canOutput(f))
				ret.add(f);
		}
		return ret;
	}
}
