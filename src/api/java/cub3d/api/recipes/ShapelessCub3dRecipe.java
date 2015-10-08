package cub3d.api.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cub3d.api.ICub3dRecipe;
import cub3d.api.matchers.IItemMatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class ShapelessCub3dRecipe implements ICub3dRecipe {
    public final ItemStack result;
    public final List<IItemMatcher> matchers;

	public ShapelessCub3dRecipe(ItemStack result, List<IItemMatcher> matchers) {
        this.result = result;
        this.matchers = matchers;
	}
	
	@Override
	public boolean matches(ItemStack[][][] input, World world) {
		ArrayList<ItemStack> inputList=new ArrayList<ItemStack>();
		for(ItemStack[][] i:input){
			for(ItemStack[] j:i){
				for(ItemStack k:j){
					if(k!=null)
						inputList.add(k);
				}
			}
		}
		
		ArrayList<IItemMatcher> temp=new ArrayList<IItemMatcher>(matchers);
		for(ItemStack i:inputList){
			if(!hasMatcher(i,temp))
				return false;
		}
		return temp.size()==0;
	}
	
	public boolean hasMatcher(ItemStack i,ArrayList<IItemMatcher> j){
		for(IItemMatcher k:j){
			if(k.matches(i)){
				j.remove(k);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ItemStack getCraftingResult(ItemStack[][][] input) {
		return result;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return result;
	}

}
