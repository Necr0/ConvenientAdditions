package cub3d.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class Cub3dCraftingManager {
    /** The static instance of this class */
    private static final Cub3dCraftingManager instance = new Cub3dCraftingManager();
    /** A list of all the recipes added */
    public List<ICub3dRecipe> recipes = new ArrayList<ICub3dRecipe>();

    /**
     * Returns the static instance of this class
     */
    public static final Cub3dCraftingManager getInstance()
    {
        /** The static instance of this class */
        return instance;
    }
	
	private Cub3dCraftingManager(){}
	
	public void addRecipe(ICub3dRecipe recipe){
		this.recipes.add(recipe);
	}
	
	private ItemStack[][][] rotateArray(ItemStack[][][] in){
		ItemStack[][][] out=new ItemStack[3][][];
		for(int i=0;i<3;i++){
			ItemStack[][] o=new ItemStack[3][];
			o[0]=new ItemStack[]{in[i][2][0],in[i][1][0],in[i][0][0]};
			o[1]=new ItemStack[]{in[i][2][1],in[i][1][1],in[i][0][1]};
			o[2]=new ItemStack[]{in[i][2][2],in[i][1][2],in[i][0][2]};
			out[i]=o;
		}
		
		return out;
	}
	
	public ItemStack findMatchingRecipe(ItemStack[][][] input,World world){
		ItemStack temp=findMatch(input,world);
		if(temp!=null)
			return temp;
		temp=findMatch(rotateArray(input),world);
		if(temp!=null)
			return temp;
		temp=findMatch(rotateArray(rotateArray(input)),world);
		if(temp!=null)
			return temp;
		temp=findMatch(rotateArray(rotateArray(rotateArray(input))),world);
		return temp;
	}
	
	private ItemStack findMatch(ItemStack[][][] in,World w){
		for(ICub3dRecipe r:recipes){
			if(r.matches(in, w))
				return r.getCraftingResult(in);
		}
		return null;
	}

    public List<ICub3dRecipe> getRecipeList()
    {
        return this.recipes;
    }
}
