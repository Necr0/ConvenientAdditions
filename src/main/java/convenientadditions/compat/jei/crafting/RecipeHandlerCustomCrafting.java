package convenientadditions.compat.jei.crafting;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

/**
 * Created by Necro on 5/6/2017.
 */
public class RecipeHandlerCustomCrafting implements IRecipeWrapperFactory<ICustomCraftingRecipe> {
    @Override
    public IRecipeWrapper getRecipeWrapper(ICustomCraftingRecipe recipe) {
        return new RecipeWrapperCustomCrafting(recipe);
    }
}
