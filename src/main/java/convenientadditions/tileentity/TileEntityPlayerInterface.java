package convenientadditions.tileentity;

import java.util.List;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;

public class TileEntityPlayerInterface extends TileEntity implements IInventory, ITickable {

	@Override
	public int getSizeInventory() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getSizeInventory();
		else
			return 0;
	}
	
	@Override
	public void update(){
		/*boolean t=hasTarget();
		int m=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(t&&m!=1)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
		else if(!t&&m==1)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);*/
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

	/*@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getStackInSlotOnClosing(slot);
		else
			return null;
	}*/

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.setInventorySlotContents(slot, stack);
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
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.isItemValidForSlot(slot, stack);
		else
			return false;
	}

	public InventoryPlayer getPlayerInventory(){
		List<EntityPlayer> l=this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX(), pos.getY()+1, pos.getZ(), pos.getX()+1, pos.getY()+2, pos.getZ()+1));
		if(l.size()>0)
			return l.get(0).inventory;
		else
			return null;
	}
	
	public boolean hasTarget(){
		return this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX(), pos.getY()+1, pos.getZ(), pos.getX()+1, pos.getY()+2, pos.getZ()+1)).size()>0;
	}

	@Override
	public String getName() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getName();
		else
			return "inventory."+ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+".name";
	}

	@Override
	public boolean hasCustomName() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.hasCustomName();
		else
			return false;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}
}
