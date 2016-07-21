package convenientadditions.block.setProvider;

import java.util.HashMap;

import conveniencecore.block.tileentity.IConfigurable;
import conveniencecore.block.tileentity.ItemStackHandlerAutoSave;
import conveniencecore.block.tileentity.ItemStackHandlerAutoSaveOutputOnly;
import conveniencecore.util.FillSetFilter;
import convenientadditions.block.TileEntityCABase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntitySetProvider extends TileEntityCABase implements IConfigurable, ITickable {

	public HashMap<EnumFacing, EnumOutletMode> outletSides=new HashMap<EnumFacing, EnumOutletMode>();
	
	public boolean ready=true;
	public boolean ignoreDV=false;
	public boolean ignoreNBT=false;
	public byte resetMode;
	public boolean powered;
	public boolean pulseOpen;
	public boolean filteredInput;

	public ItemStackHandlerSPFiltered input;
	public ItemStackHandlerAutoSaveOutputOnly output;
	public ItemStackHandlerAutoSave filter;
	
	public TileEntitySetProvider(){
		super();
		for(EnumFacing f:EnumFacing.VALUES){
			outletSides.put(f, EnumOutletMode.disabled);
		}
		this.input=new ItemStackHandlerSPFiltered(this,18);
		this.output=new ItemStackHandlerAutoSaveOutputOnly(this,18);
		this.filter=new ItemStackHandlerAutoSave(this,9);
	}
	
	public TileEntitySetProvider(EnumFacing direction){
		this();
	}

	@Override
	public void update() {
		if(worldObj.isRemote)
			return;
		
		if(resetMode==0&&worldObj.isBlockIndirectlyGettingPowered(getPos())>0){
			reset();
		}else if(resetMode==1&&worldObj.isBlockIndirectlyGettingPowered(getPos())==0){
			reset();
		}else if(resetMode==4&&worldObj.isBlockIndirectlyGettingPowered(getPos())==0){
			if(slotConfigReady(false))
				reset();
		}
		
		if(!slotConfigReady(true))
			return;
		
		FillSetFilter FILLter=new FillSetFilter(input.getStacks(), filter.getStacks(),ignoreDV,ignoreNBT);
		if(FILLter.isReadyForOutput()&&ready){
			input.setStacks(FILLter.getInput());
			output.setStacks(FILLter.getOutput());
			ready=false;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		for(EnumFacing f:EnumFacing.VALUES){
			if(nbt.hasKey("OUTLET_"+f.ordinal())){
				outletSides.put(f,EnumOutletMode.fromByte(nbt.getByte("OUTLET_"+f.ordinal())));
			}
		}
		if(nbt.hasKey("INPUT"))
			input.deserializeNBT((NBTTagCompound)nbt.getTag("INPUT"));
		if(nbt.hasKey("OUTPUT"))
			output.deserializeNBT((NBTTagCompound)nbt.getTag("OUTPUT"));
		if(nbt.hasKey("FILTER"))
			filter.deserializeNBT((NBTTagCompound)nbt.getTag("FILTER"));
		if(nbt.hasKey("READY"))
			ready=nbt.getBoolean("READY");
		if(nbt.hasKey("IGNOREDV"))
			ignoreDV=nbt.getBoolean("IGNOREDV");
		if(nbt.hasKey("IGNORENBT"))
			ignoreNBT=nbt.getBoolean("IGNORENBT");
		if(nbt.hasKey("FILTERINPUT"))
			filteredInput=nbt.getBoolean("FILTERINPUT");
		if(nbt.hasKey("RSMODE"))
			resetMode=nbt.getByte("RSMODE");
		if(nbt.hasKey("POWERED"))
			powered=nbt.getBoolean("POWERED");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		for(EnumFacing f:EnumFacing.VALUES){
			nbt.setByte("OUTLET_"+f.ordinal(), (byte)outletSides.get(f).ordinal());
		}
		nbt.setTag("INPUT", input.serializeNBT());
		nbt.setTag("OUTPUT", output.serializeNBT());
		nbt.setTag("FILTER", filter.serializeNBT());
		nbt.setBoolean("READY", ready);
		nbt.setBoolean("IGNOREDV", ignoreDV);
		nbt.setBoolean("IGNORENBT", ignoreNBT);
		nbt.setBoolean("FILTERINPUT", filteredInput);
		nbt.setByte("RSMODE",resetMode);
		nbt.setBoolean("POWERED",powered);
		return nbt;
	}

	@Override
	public boolean configureSide(EnumFacing f) {
		outletSides.put(f, EnumOutletMode.fromByte((byte)(outletSides.get(f).ordinal()+1)));
		markDirty();
		this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos),0);
		return true;
	}
	
	public void reset(){
		if(slotConfigReady(false))
			ready=true;
		pulseOpen=false;
	}

	public enum EnumOutletMode implements IStringSerializable{
		disabled,
		output,
		input;
		
		public static EnumOutletMode fromByte(byte bt){
			return EnumOutletMode.values()[bt % values().length];
		}

		@Override
		public String getName() {
			return this.name();
		}
	}
	
	public boolean slotConfigReady(boolean includeFilter){
		for(ItemStack s:output.getStacks()){
			if(s!=null)
				return false;
		}
		if(includeFilter){
			for(ItemStack s:filter.getStacks()){
				if(s!=null)
					return true;
			}
			return false;
		}
		return true;
	}
	
	public void setIgnoreDV(boolean ignoreDV) {
		this.ignoreDV = ignoreDV;
		markDirty();
		this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos),0);
	}

	public void setIgnoreNBT(boolean ignoreNBT) {
		this.ignoreNBT = ignoreNBT;
		markDirty();
		this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos),0);
	}
	
	public void setFilterInput(boolean filterInput) {
		this.filteredInput=filterInput;
		markDirty();
		this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos),0);
	}

	public void setResetMode(byte resetMode) {
		this.resetMode = resetMode;
		markDirty();
		this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos),0);
	}

	public void updateRS(boolean power) {
		if(powered!=power){
			if(!ready){
				if(power&&resetMode==2)
					this.pulseOpen=true;
				else if(!power&&resetMode==2&&pulseOpen){
					reset();
				}
			}
			powered=power;
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return (capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY&&outletSides.get(facing)!=EnumOutletMode.disabled)?true:super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return (capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY&&outletSides.get(facing)!=EnumOutletMode.disabled)?
					(outletSides.get(facing)==EnumOutletMode.input?(T)input:(T)output)
					:super.getCapability(capability, facing);
	}
}
