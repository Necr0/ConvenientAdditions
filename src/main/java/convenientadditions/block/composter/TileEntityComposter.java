package convenientadditions.block.composter;

import java.util.List;
import java.util.Random;

import conveniencecore.util.Helper;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityComposter extends TileEntity implements ITickable {
	
	ComposterItemStackHandler stackHandler;
	
	public TileEntityComposter() {
		super();
		this.stackHandler=new ComposterItemStackHandler(this);
	}
	
	public boolean processing=false;
	public int content=0;
	public int progress=0;
	public boolean spores=true;
	
	public static int capacity=25000;
	public static int progressPeriod=2100;
	public static int progressContent=2500;

	
	public ItemStack insertStack(ItemStack stackIn){
		ItemStack stack=stackHandler.insertItem(0, stackIn, false);
		return stack;
	}
	
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
	
	public void readSyncNBT(NBTTagCompound nbt){
		this.processing=nbt.getBoolean("processing");
		this.content=nbt.getInteger("content");
	}
	
	public void writeSyncNBT(NBTTagCompound nbt){
		nbt.setBoolean("processing", processing);
		nbt.setInteger("content", content);
	}
	
	public int getContentCapacityPercentage(){
		return content*100/capacity;
	}
	
	@Override
	public void update(){
		IBlockState state=worldObj.getBlockState(pos);
		Random rnd=new Random();
		if(!worldObj.isRemote){
			if(content>=progressContent){
				progress++;
				if(progress>=progressPeriod){
					progress=0;
					content-=progressContent;
					Helper.spawnItemInPlace(worldObj, (double)pos.getX()+.5, (double)pos.getY()+1.2, (double)pos.getZ()+.5, new ItemStack(ModItems.itemCompost,1,this.spores?1:0));
					ItemStack additional=null;
					switch(rnd.nextInt(6)){
						case 0:
						case 1:
							additional = new ItemStack(ModItems.itemDirtChunk);
							break;
						case 2:
							additional = new ItemStack(ModItems.itemFertilizer);
							break;
						default:
							break;
					}
					if(additional != null)
						Helper.spawnItemInPlace(worldObj, (double)pos.getX()+.5, (double)pos.getY()+1.2, (double)pos.getZ()+.5, additional);
					if(rnd.nextInt(22) == 0)
						this.spores = false;
				}
				if(content>=capacity){
					List<EntityPlayer> players=worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX()-2, pos.getY()-2, pos.getZ()-2, pos.getX()+3, pos.getY()+3, pos.getZ()+3));
					for(EntityPlayer p:players){
						switch(rnd.nextInt(120)){
							case 0:
								p.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 200, 0));
								break;
							default:
								break;
						}
					}
				}
				this.markDirty();
				this.worldObj.notifyBlockUpdate(pos, state, state, 3);
			}else{
				this.progress=0;
				if(this.content==0)
	    			this.spores=false;
				if(processing){
					this.processing=false;
					this.markDirty();
					this.worldObj.notifyBlockUpdate(pos, state, state, 3);
				}
			}
		}else if(processing){
			if(rnd.nextInt(10)==0)
				worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, pos.getX()+.5-((double)(rnd.nextInt(9)-4)/10D), pos.getY()+.2+(double)content/(double)capacity*.75, pos.getZ()+.5+((double)(rnd.nextInt(9)-4)/10D), 0, 0.6, 0);
			if(content>=capacity)
				worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, pos.getX()+.5-((double)(rnd.nextInt(9)-4)/10D), pos.getY()+.2+(double)content/(double)capacity*.75, pos.getZ()+.5+((double)(rnd.nextInt(9)-4)/10D), 0, 0.6, 0);
		}
	}
	
	public int getContentValue(ItemStack itemStack){
		return CompostRegistry.getCompostingMass(itemStack);
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt=new NBTTagCompound();
		writeSyncNBT(nbt);
		return new SPacketUpdateTileEntity(this.pos, 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		readSyncNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY?true:super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY?(T)stackHandler:super.getCapability(capability, facing);
	}
}
