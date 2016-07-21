package convenientadditions.block.itemReceiver;

import java.util.ArrayList;

import conveniencecore.block.tileentity.ItemStackHandlerAutoSaveRestricted;
import conveniencecore.item.itemhandler.CombinedItemStackHandler;
import convenientadditions.api.item.ItemChannelModule;
import convenientadditions.api.provider.itemnetwork.IMatcher;
import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.block.TileEntityCABase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityItemReceiver extends TileEntityCABase {
	
	ItemStackHandlerAutoSaveRestricted channels;
	
	public TileEntityItemReceiver() {
		channels=new ItemStackHandlerAutoSaveRestricted(this, 3, ItemChannelModule.class);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY?true:super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY?(T)getItemHandler():super.getCapability(capability, facing);
	}
	
	public IItemHandler getItemHandler(){
		ArrayList<IItemHandler> tmp=new ArrayList<IItemHandler>();
		for(IMatcher m:getMatchers()){
			for(Tuple<World, BlockPos> t:ItemNetworkProvider.getEntries(m)){
				if(ItemNetworkProvider.getProvider(t.getFirst(), t.getSecond()).hasItemHandler())
					if(!tmp.contains(ItemNetworkProvider.getProvider(t.getFirst(), t.getSecond()).getItemHandler()))
						tmp.add(ItemNetworkProvider.getProvider(t.getFirst(), t.getSecond()).getItemHandler());
			}
		}
		return new CombinedItemStackHandler(tmp);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("CHANNELS")&&nbt.getTag("CHANNELS") instanceof NBTTagCompound)
			channels.deserializeNBT((NBTTagCompound)nbt.getTag("CHANNELS"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setTag("CHANNELS", channels.serializeNBT());
		return nbt;
	}

	public IMatcher[] getMatchers() {
		ArrayList<IMatcher> a=new ArrayList<IMatcher>();
		for(ItemStack s:channels.getStacks()){
			if(s!=null&&((ItemChannelModule)s.getItem()).hasMatcher(s))
				a.add(((ItemChannelModule)s.getItem()).getMatcher(s));
		}
		return a.toArray(new IMatcher[a.size()]);
	}
}
