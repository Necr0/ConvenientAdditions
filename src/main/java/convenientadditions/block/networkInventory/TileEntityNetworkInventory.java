package convenientadditions.block.networkInventory;

import java.util.ArrayList;

import conveniencecore.item.itemhandler.CombinedItemStackHandler;
import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.block.TileEntityCABase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityNetworkInventory extends TileEntityCABase {
	
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
		for(Tuple<World, BlockPos> t:ItemNetworkProvider.getEntries()){
			tmp.add(ItemNetworkProvider.getProvider(t.getFirst(), t.getSecond()).getItemHandler());
		}
		return new CombinedItemStackHandler(tmp);
	}
}
