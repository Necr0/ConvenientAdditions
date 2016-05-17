package convenientadditions.tileentity;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.api.item.ICompostable;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityComposter extends TileEntity implements IInventory, ITickable {
	
	public boolean processing=false;
	public int content=0;
	public int progress=0;
	public boolean spores=true;
	
	public static int capacity=25000;
	public static int progressPeriod=2100;
	public static int progressContent=2500;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		this.processing=nbt.getBoolean("processing");
		this.content=nbt.getInteger("content");
		this.progress=nbt.getInteger("progress");
		this.spores=nbt.getBoolean("spores");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("processing", processing);
		nbt.setInteger("content", content);
		nbt.setInteger("progress", progress);
		nbt.setBoolean("progress", spores);
	}
	
	public int getContentCapacityPercentage(){
		return content*100/capacity;
	}
	
	@Override
	public void update(){
		Random rnd=new Random();
		if(!worldObj.isRemote){
			if(content>=progressContent){
				progress++;
				if(progress>=progressPeriod){
					progress=0;
					content-=progressContent;
					EntityItem item=null;
					Helper.spawnItemInPlace(worldObj, (double)pos.getX()+.5, (double)pos.getY()+1.2, (double)pos.getZ()+.5, new ItemStack(ModItems.itemCompost,1,this.spores?1:0));
					switch(rnd.nextInt(6)){
						case 0:
							Helper.spawnItemInPlace(worldObj, (double)pos.getX()+.5, (double)pos.getY()+1.2, (double)pos.getZ()+.5, new ItemStack(ModItems.itemDirtChunk));
							break;
						case 1:
							Helper.spawnItemInPlace(worldObj, (double)pos.getX()+.5, (double)pos.getY()+1.2, (double)pos.getZ()+.5, new ItemStack(ModItems.itemDirtChunk));
							break;
						case 2:
							Helper.spawnItemInPlace(worldObj, (double)pos.getX()+.5, (double)pos.getY()+1.2, (double)pos.getZ()+.5, new ItemStack(ModItems.itemFertilizer));
							break;
						default:
							break;
					}
					if(rnd.nextInt(28)==0)
						this.spores=false;
				}
				if(content>=capacity){
					List<EntityPlayer> players=worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX()-2, pos.getY()-2, pos.getZ()-2, pos.getX()+3, pos.getY()+3, pos.getZ()+3));
					for(EntityPlayer p:players){
						switch(rnd.nextInt(120)){
							case 0:
								p.addPotionEffect(new PotionEffect(9, 200, 0));
								break;
							default:
								break;
						}
					}
				}
				this.markDirty();
				worldObj.markBlockForUpdate(pos);
			}else{
				this.progress=0;
				if(this.content==0)
	    			this.spores=false;
				if(processing){
					this.processing=false;
					this.markDirty();
					worldObj.markBlockForUpdate(pos);
				}
			}
		}else if(processing){
			if(rnd.nextInt(10)==0)
				worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, pos.getX()+.5-((double)(rnd.nextInt(9)-4)/10D), pos.getY()+.2+(double)content/(double)capacity*.75, pos.getZ()+.5+((double)(rnd.nextInt(9)-4)/10D), 0, 0.6, 0);
			if(content>=capacity)
				worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, pos.getX()+.5-((double)(rnd.nextInt(9)-4)/10D), pos.getY()+.2+(double)content/(double)capacity*.75, pos.getZ()+.5+((double)(rnd.nextInt(9)-4)/10D), 0, 0.6, 0);
		}
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt=new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(pos, 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.getNbtCompound());
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
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
    	this.content+=getContentValue(itemStack);
    	this.processing=(content>=progressContent);
    	if(itemStack.getItem() instanceof ICompostable && ((ICompostable)itemStack.getItem()).hasShroomSpores(itemStack))
    		this.spores=true;
    	else if(itemStack.getItem()==Items.mushroom_stew||itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.red_mushroom)||itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.brown_mushroom))
    		this.spores=true;
        this.markDirty();
        worldObj.markBlockForUpdate(pos);
    }
    
	@Override
	public String getName() {
		return "inventory."+ConvenientAdditionsMod.MODID+":Composter.name";
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
		return content<capacity&&getContentValue(itemStack)>0;
	}
	
	public int getContentValue(ItemStack itemStack){
		return CompostRegistry.getCompostingMass(itemStack);
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

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
}
