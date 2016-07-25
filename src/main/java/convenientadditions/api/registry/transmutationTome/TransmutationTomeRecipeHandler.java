package convenientadditions.api.registry.transmutationTome;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

public class TransmutationTomeRecipeHandler {
	public static final TransmutationTomeRecipeHandler INSTANCE=new TransmutationTomeRecipeHandler();
	
	public ArrayList<ITransmutationTomeRecipe> recipes=new ArrayList<ITransmutationTomeRecipe>();
	
	public void addRecipe(ITransmutationTomeRecipe recipe){
		recipes.add(recipe);
	}
	
	public void addRecipe(ItemStack base,ItemStack transmutator,ItemStack result,int time,int level){
		recipes.add(new TransmutationTomeRecipe(base, transmutator, result, time, level));
	}
	
	public Tuple<ItemStack,ItemStack> getLeftovers(ItemStack base,ItemStack transmutator){
		for(ITransmutationTomeRecipe r:recipes){
			if(r.doesMatch(base, transmutator))
				return r.getLeftovers(base, transmutator);
		}
		return null;
	}
	
	public ItemStack getResult(ItemStack base,ItemStack transmutator){
		for(ITransmutationTomeRecipe r:recipes){
			if(r.doesMatch(base, transmutator))
				return r.getResult(base, transmutator).copy();
		}
		return null;
	}
	
	public boolean doesMatch(ItemStack base,ItemStack transmutator){
		for(ITransmutationTomeRecipe r:recipes){
			if(r.doesMatch(base, transmutator))
				return true;
		}
		return false;
	}
	
	public int getLevelRequired(ItemStack base,ItemStack transmutator){
		for(ITransmutationTomeRecipe r:recipes){
			if(r.doesMatch(base, transmutator))
				return r.getLevelRequired(base, transmutator);
		}
		return 0;
	}
	
	public int getTimeRequired(ItemStack base,ItemStack transmutator){
		for(ITransmutationTomeRecipe r:recipes){
			if(r.doesMatch(base, transmutator))
				return r.getTimeRequired(base, transmutator);
		}
		return 0;
	}
}
