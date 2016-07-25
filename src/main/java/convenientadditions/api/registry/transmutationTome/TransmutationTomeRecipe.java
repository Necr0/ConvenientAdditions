package convenientadditions.api.registry.transmutationTome;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

public class TransmutationTomeRecipe implements ITransmutationTomeRecipe, ITransmutationTomeJEIRecipe {
	ItemStack base,transmutator,result;
	int time,level;
	
	public TransmutationTomeRecipe(ItemStack base,ItemStack transmutator,ItemStack result,int time,int level) {
		this.base=base;
		this.transmutator=transmutator;
		this.result=result;
		this.time=time;
		this.level=level;
	}

	@Override
	public ItemStack getResult(ItemStack base, ItemStack transmutator) {
		return result;
	}

	@Override
	public Tuple<ItemStack, ItemStack> getLeftovers(ItemStack base, ItemStack transmutator) {
		ItemStack outputBase = null;
		if(base!=null){
			outputBase = base.copy();
			if(outputBase.stackSize-this.base.stackSize<1){
				if(outputBase.getItem().hasContainerItem(outputBase))
					outputBase=outputBase.getItem().getContainerItem(outputBase);
				else
					outputBase = null;
			}else
				outputBase.stackSize -= this.base.stackSize;
		}
		ItemStack outputTransmutator = null;
		if(transmutator!=null){
			outputTransmutator = transmutator.copy();
			if(outputTransmutator.stackSize-1<1){
				if(outputTransmutator.getItem().hasContainerItem(outputTransmutator))
					outputTransmutator=outputTransmutator.getItem().getContainerItem(outputTransmutator);
				else
					outputTransmutator = null;
			}else
				outputTransmutator.stackSize -= 1;
		}
		return new Tuple<ItemStack, ItemStack>(outputBase,outputTransmutator);
	}

	@Override
	public boolean doesMatch(ItemStack base, ItemStack transmutator) {
		if(base==null||transmutator==null)
			return false;
		return base.isItemEqual(this.base)&&base.stackSize>=this.base.stackSize&&transmutator.isItemEqual(this.transmutator)&&level>=this.level;
	}

	@Override
	public int getTimeRequired(ItemStack base, ItemStack transmutator) {
		return time;
	}

	@Override
	public int getLevelRequired(ItemStack base, ItemStack transmutator) {
		return level;
	}

	@Override
	public List<ItemStack> getBase() {
		return Collections.singletonList(this.base);
	}

	@Override
	public List<ItemStack> getTransmutator() {
		return Collections.singletonList(this.transmutator);
	}

	@Override
	public List<ItemStack> getResult() {
		return Collections.singletonList(this.result);
	}

	@Override
	public int getLevel() {
		return this.level;
	}

}
