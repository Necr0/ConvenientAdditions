package cub3d.api.recipes;

import cub3d.api.ICub3dRecipe;
import cub3d.api.matchers.IItemMatcher;
import scala.tools.nsc.backend.icode.analysis.TypeFlowAnalysis.MethodTFA.TypeOfStackPos;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShapedCub3dRecipe implements ICub3dRecipe {

	public IItemMatcher[][][] matchers;
	public ItemStack result;
	
	public ShapedCub3dRecipe(IItemMatcher[][][] matchers,ItemStack result) {
		this.matchers=matchers;
		this.result=result;
	}
	
	@Override
	public boolean matches(ItemStack[][][] input, World world) {
		for(int i=0;i<input.length;i++){
			for(int j=0;j<input[i].length;j++){
				for(int k=0;k<input[i][j].length;k++){
					if(input[i][j][k]==null){
						if(matchers[i][j][k]!=null)
							return false;
					}else if(matchers[i][j][k]==null){
						if(input[i][j][k]!=null)
							return false;
					}else if(!(matchers[i][j][k].matches(input[i][j][k])))
						return false;
				}
			}
		}
		return true;
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
