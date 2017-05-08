package convenientadditions.compat.jei.transmutationTome;

import convenientadditions.api.registry.transmutationTome.ITransmutationTomeJEIRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class RecipeHandlerTransmutationTome implements IRecipeWrapperFactory<ITransmutationTomeJEIRecipe> {
    @Override
    public IRecipeWrapper getRecipeWrapper(ITransmutationTomeJEIRecipe recipe) {
        return new RecipeWrapperTransmutationTome(recipe);
    }
}
