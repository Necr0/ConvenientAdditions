package convenientadditions.tileentity;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.api.tileentity.IConfigurable;
import convenientadditions.api.util.MathHelper;
import convenientadditions.entity.CAEntitySpecialItem;

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
		System.out.println("read");
		if(nbt.hasKey("OUTLET")){
			byte in=nbt.getByte("OUTLET");
			MathHelper.Bitmask mask=new MathHelper.Bitmask(in);
			for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
				outletSides.put(f,mask.getBit(f.ordinal()));
			}
		}
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			System.out.println(f.name()+":"+outletSides.get(f));
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
        	CAEntitySpecialItem item=new CAEntitySpecialItem(this.worldObj,this.xCoord+0.5,this.yCoord-0.3,this.zCoord+0.5,itemStack);
        	for(IEntitySpecialItemBehaviour b:SeedBoxItemBehaviourRegistry.getItemBehaviour(itemStack)){
        		item.addBehaviour(b);
        	}
        	item.setVelocity(0d, 0d, 0d);
        	item.delayBeforeCanPickup=20;
            this.worldObj.spawnEntityInWorld(item);
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
		return !isOutput(ForgeDirection.getOrientation(side));
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}
	
	public boolean isOutput(ForgeDirection f){
		return this.outletSides.get(f);
	}
}
