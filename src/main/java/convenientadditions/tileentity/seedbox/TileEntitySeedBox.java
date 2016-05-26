package convenientadditions.tileentity.seedbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import conveniencecore.entity.EntitySpecialItem;
import conveniencecore.tileentity.IConfigurable;
import conveniencecore.util.MathHelper;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public class TileEntitySeedBox extends TileEntity implements ISidedInventory, IConfigurable {

	public HashMap<EnumFacing, Boolean> outletSides=new HashMap<EnumFacing, Boolean>();
	
	public TileEntitySeedBox() {
		super();
		for(EnumFacing f:EnumFacing.VALUES){
			outletSides.put(f, (f!=EnumFacing.DOWN?false:true));
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("OUTLET")){
			byte in=nbt.getByte("OUTLET");
			MathHelper.Bitmask mask=new MathHelper.Bitmask(in);
			for(EnumFacing f:EnumFacing.VALUES){
				outletSides.put(f,mask.getBit(f.ordinal()));
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		MathHelper.Bitmask mask=new MathHelper.Bitmask(0);
		for(EnumFacing f:EnumFacing.VALUES){
			mask.setBit(f.ordinal(), outletSides.get(f));
		}
		nbt.setByte("OUTLET", (byte)mask.get());
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
	public boolean configureSide(EnumFacing f) {
		this.outletSides.put(f, !outletSides.get(f));
		this.markDirty();
		IBlockState state=worldObj.getBlockState(pos);
		this.worldObj.notifyBlockUpdate(pos, state, state, 3);
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        if (itemStack != null && itemStack.stackSize > 0)
        {
        	List<EnumFacing> outputs=getValidOutputDirections();
        	if(outputs.size()>0){
        		EnumFacing output=(EnumFacing) outputs.toArray()[new Random().nextInt(outputs.size())];
        		EntitySpecialItem item=new EntitySpecialItem(this.worldObj,this.pos.getX()+0.5+(output.getFrontOffsetX()*0.8),this.pos.getY()+0.5+(output.getFrontOffsetY()*0.8),this.pos.getZ()+0.5+(output.getFrontOffsetZ()*0.8),itemStack);
	        	for(long b:SeedBoxItemBehaviourRegistry.getItemBehaviour(itemStack)){
	        		item.addBehaviour(b);
	        	}
	        	item.setVelocity(0d, 0d, 0d);
	        	item.setPickupDelay(20);
	            this.worldObj.spawnEntityInWorld(item);
        	}
        }
    }
    
	@Override
	public String getName() {
		return "inventory."+ConvenientAdditionsMod.MODID+":"+Reference.seedBoxBlockName+".name";
	}

	@Override
	public boolean hasCustomName() {
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
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return true;
	}

	/*@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return !isOutput(EnumFacing.getOrientation(side))?new int[]{0}:new int[]{};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack,int side) {
		return !isOutput(EnumFacing.getOrientation(side))&&getValidOutputDirections().size()>0;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}*/
	
	public boolean isOutput(EnumFacing f){
		return this.outletSides.get(f);
	}
	
	public boolean canOutput(EnumFacing f){
		BlockPos posF=new BlockPos(pos.getX()+f.getFrontOffsetX(),pos.getY()+f.getFrontOffsetY(),pos.getZ()+f.getFrontOffsetZ());
		return isOutput(f)&&!worldObj.getBlockState(pos).getBlock().isBlockSolid(worldObj, pos, f.getOpposite());
	}
	
	public List<EnumFacing> getValidOutputDirections(){
		ArrayList<EnumFacing> ret=new ArrayList<EnumFacing>();
		for(EnumFacing f:EnumFacing.VALUES){
			if(canOutput(f))
				ret.add(f);
		}
		return ret;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {return null;}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
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
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {return false;}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}
}
