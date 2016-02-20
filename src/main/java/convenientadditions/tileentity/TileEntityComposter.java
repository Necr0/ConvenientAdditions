package convenientadditions.tileentity;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.api.item.ICompostable;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoublePlant;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityComposter extends TileEntity implements IInventory {
	
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
	public void updateEntity(){
		Random rnd=new Random();
		if(!worldObj.isRemote){
			if(content>=progressContent){
				progress++;
				if(progress>=progressPeriod){
					progress=0;
					content-=progressContent;
					EntityItem item=null;
					Helper.spawnItemInPlace(worldObj, (double)xCoord+.5, (double)yCoord+1.2, (double)zCoord+.5, new ItemStack(ModItems.itemCompost,1,this.spores?1:0));
					switch(rnd.nextInt(6)){
						case 0:
							Helper.spawnItemInPlace(worldObj, (double)xCoord+.5, (double)yCoord+1.2, (double)zCoord+.5, new ItemStack(ModItems.itemDirtChunk));
							break;
						case 1:
							Helper.spawnItemInPlace(worldObj, (double)xCoord+.5, (double)yCoord+1.2, (double)zCoord+.5, new ItemStack(ModItems.itemDirtChunk));
							break;
						case 2:
							Helper.spawnItemInPlace(worldObj, (double)xCoord+.5, (double)yCoord+1.2, (double)zCoord+.5, new ItemStack(ModItems.itemFertilizer));
							break;
						default:
							break;
					}
					if(rnd.nextInt(28)==0)
						this.spores=false;
				}
				if(content>=capacity){
					List<EntityPlayer> players=worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord-2, yCoord-2, zCoord-2, xCoord+3, yCoord+3, zCoord+3));
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
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}else{
				this.progress=0;
				if(this.content==0)
	    			this.spores=false;
				if(processing){
					this.processing=false;
					this.markDirty();
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
			}
		}else if(processing){
			if(rnd.nextInt(10)==0)
				worldObj.spawnParticle("mobSpell", xCoord+.5-((double)(rnd.nextInt(9)-4)/10D), yCoord+.2+(double)content/(double)capacity*.75, zCoord+.5+((double)(rnd.nextInt(9)-4)/10D), 0, 0.6, 0);
			if(content>=capacity)
				worldObj.spawnParticle("mobSpell", xCoord+.5-((double)(rnd.nextInt(9)-4)/10D), yCoord+.2+(double)content/(double)capacity*.75, zCoord+.5+((double)(rnd.nextInt(9)-4)/10D), 0, 0.6, 0);
		}
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
    	this.content+=getContentValue(itemStack);
    	this.processing=(content>=progressContent);
    	if(itemStack.getItem() instanceof ICompostable &&  ((ICompostable)itemStack.getItem()).hasShroomSpores(itemStack))
    		this.spores=true;
    	else if(itemStack.getItem()==Items.mushroom_stew||itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.red_mushroom)||itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.brown_mushroom))
    		this.spores=true;
        this.markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
	@Override
	public String getInventoryName() {
		return "inventory."+ConvenientAdditionsMod.MODID+":Composter.name";
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
		return content<capacity&&getContentValue(itemStack)>0;
	}
	
	public int getContentValue(ItemStack itemStack){
		if(itemStack.getItem() instanceof ICompostable&&((ICompostable)itemStack.getItem()).isCompostable(itemStack))
			return ((ICompostable)itemStack.getItem()).getCompostingMass(itemStack);
		for(int i:OreDictionary.getOreIDs(itemStack)){
			if(OreDictionary.getOreName(i).startsWith("crop"))
				return 1200;
			else if(OreDictionary.getOreName(i).startsWith("food"))
				if(itemStack.getItem() instanceof ItemFood)
					return ((ItemFood)itemStack.getItem()).func_150905_g(itemStack)*400;
				else
					return 1200;
			else if(OreDictionary.getOreName(i).equals("treeLeaves"))
				return 200;
			else if(OreDictionary.getOreName(i).equals("treeSapling"))
				return 800;
			else if(OreDictionary.getOreName(i).startsWith("seed"))
				return 130;
		}
		if(itemStack.getItem() instanceof ItemFood)
			return ((ItemFood)itemStack.getItem()).func_150905_g(itemStack)*400;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.tallgrass))
			return 230;
		else if(itemStack.getItem() instanceof ItemDoublePlant)
			return 350;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.cactus))
			return 500;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.hay_block))
			return 10700;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.waterlily))
			return 400;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.red_mushroom)||itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.brown_mushroom))
			return 400;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.red_flower)||itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.yellow_flower))
			return 250;
		else if(itemStack.getItem()==Items.reeds)
			return 250;
		else if(itemStack.getItem()==Items.cake)
			return 2400;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.vine))
			return 300;
		else if(itemStack.getItem()==ItemBlock.getItemFromBlock(Blocks.pumpkin))
			return 1400;
		else if(itemStack.getItem()==Items.feather)
			return 110;
		else if(itemStack.getItem()==Items.egg)
			return 230;
		else if(itemStack.getItem()==Items.slime_ball)
			return 130;
		else if(itemStack.getItem()==Items.wheat_seeds)
			return 170;
		else if(itemStack.getItem()==Items.melon_seeds)
			return 100;
		else if(itemStack.getItem()==Items.pumpkin_seeds)
			return 100;
		else if(itemStack.getItem()==Items.nether_wart)
			return 1100;
		else if(itemStack.getItem()==Items.dye)
			return 227;
		else if(itemStack.getItem()==Items.bone)
			return 230;
		return 0;
	}
}
