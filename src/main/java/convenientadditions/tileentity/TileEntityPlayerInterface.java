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

	public boolean hasPlayer=false;
	
	@Override
	public void update(){
		if(this.worldObj.isRemote){
			if(hasTarget()!=hasPlayer){
				hasPlayer=hasTarget();
				this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos),0);
			}
		}
	}
	
	@Override
	public int getSizeInventory() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getSizeInventory();
		else
			return 0;
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
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.removeStackFromSlot(index);
		else
			return null;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.openInventory(player);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.closeInventory(player);
	}

	@Override
	public int getField(int id) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getField(id);
		else
			return 0;
	}

	@Override
	public void setField(int id, int value) {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.setField(id, value);
	}

	@Override
	public int getFieldCount() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getFieldCount();
		else
			return 0;
	}

	@Override
	public void clear() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			p.clear();
	}

	@Override
	public ITextComponent getDisplayName() {
		InventoryPlayer p=getPlayerInventory();
		if(p!=null)
			return p.getDisplayName();
		else
			return null;
	}
}
