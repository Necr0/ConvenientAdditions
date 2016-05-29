package convenientadditions.tileentity.composter;

import java.util.List;
import java.util.Random;

import conveniencecore.entity.EntitySpecialItem;
import convenientadditions.api.item.ICompostable;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ComposterItemStackHandler implements IItemHandler, IItemHandlerModifiable {
	TileEntityComposter composter;

	public ComposterItemStackHandler(TileEntityComposter composterIn) {
		composter=composterIn;
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
		int simulate_content=composter.getContentValue(stack);
		while(stack.stackSize!=0){
			if(!(composter.content<composter.capacity&&composter.getContentValue(stack)>0)||(simulate&&simulate_content<composter.capacity))
				return stack;
			if(!simulate){
				composter.content+=composter.getContentValue(stack);
		    	composter.processing=(composter.content>=composter.progressContent);
		    	if(stack.getItem() instanceof ICompostable && ((ICompostable)stack.getItem()).hasShroomSpores(stack))
		    		composter.spores=true;
		    	else if(stack.getItem()==Items.mushroom_stew||stack.getItem()==ItemBlock.getItemFromBlock(Blocks.red_mushroom)||stack.getItem()==ItemBlock.getItemFromBlock(Blocks.brown_mushroom))
		    		composter.spores=true;
		        composter.markDirty();
				IBlockState state=composter.getWorld().getBlockState(composter.getPos());
				composter.getWorld().notifyBlockUpdate(composter.getPos(), state, state, 3);
			}else{
				simulate_content+=composter.getContentValue(stack);
			}
			stack.stackSize--;
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
