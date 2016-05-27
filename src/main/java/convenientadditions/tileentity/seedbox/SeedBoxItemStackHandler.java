package convenientadditions.tileentity.seedbox;

import java.util.List;
import java.util.Random;

import conveniencecore.entity.EntitySpecialItem;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SeedBoxItemStackHandler implements IItemHandler, IItemHandlerModifiable {
	TileEntitySeedBox box;

	public SeedBoxItemStackHandler(TileEntitySeedBox boxIn) {
		box=boxIn;
	}
	
	@Override
	public int getSlots() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return null;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (stack != null && stack.stackSize > 0)
        {
        	List<EnumFacing> outputs=box.getValidOutputDirections();
			if(outputs.size()>0){
				if(simulate)
					return null;
	    		EnumFacing output=(EnumFacing) outputs.toArray()[box.getWorld().rand.nextInt(outputs.size())];
	    		BlockPos pos=box.getPos();
	    		EntitySpecialItem item=new EntitySpecialItem(box.getWorld(),pos.getX()+0.5+(output.getFrontOffsetX()*0.8),pos.getY()+0.5+(output.getFrontOffsetY()*0.8),pos.getZ()+0.5+(output.getFrontOffsetZ()*0.8),stack);
	        	for(long b:SeedBoxItemBehaviourRegistry.getItemBehaviour(stack)){
	        		item.addBehaviourSilent(b);
	        	}
	            item.syncBehaviours();
	        	item.setVelocity(0d, 0d, 0d);
	        	item.setPickupDelay(20);
	            box.getWorld().spawnEntityInWorld(item);
			}
    	}
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return null;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		insertItem(slot, stack,false);
	}

}