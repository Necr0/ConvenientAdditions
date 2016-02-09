package convenientadditions.tileentity;

import java.util.List;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityPlayerInterface extends TileEntity implements IInventory {

	@Override
	public int getSizeInventory() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getSizeInventory();
		else
			return 0;
	}
	
	@Override
	public void updateEntity(){
		boolean t=hasTarget();
		int m=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(t&&m!=1)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
		else if(!t&&m==1)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getStackInSlot(slot);
		else
			return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.decrStackSize(slot, amount);
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getStackInSlotOnClosing(slot);
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.setInventorySlotContents(slot, stack);
	}

	@Override
	public String getInventoryName() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getInventoryName();
		else
			return "inventory."+ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+".name";
	}

	@Override
	public boolean hasCustomInventoryName() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.hasCustomInventoryName();
		else
			return false;
	}

	@Override
	public int getInventoryStackLimit() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getInventoryStackLimit();
		else
			return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.isUseableByPlayer(player);
		else
			return false;

	}

	@Override
	public void openInventory() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.openInventory();
	}

	@Override
	public void closeInventory() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.isItemValidForSlot(slot, stack);
		else
			return false;
	}

	public InventoryPlayer getPlayerInventory(){
		List<EntityPlayer> l=this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord+1, zCoord, xCoord+1, yCoord+2, zCoord+1));
		if(l.size()>0)
			return l.get(0).inventory;
		else
			return null;
	}
	
	public boolean hasTarget(){
		return this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord+1, zCoord, xCoord+1, yCoord+2, zCoord+1)).size()>0;
	}
}
