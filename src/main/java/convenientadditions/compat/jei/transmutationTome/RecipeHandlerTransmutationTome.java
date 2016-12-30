package convenientadditions.compat.jei.transmutationTome;

import convenientadditions.ModConstants;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class RecipeHandlerTransmutationTome implements IRecipeHandler<RecipeWrapperTransmutationTome> {

    @Override
    public Class<RecipeWrapperTransmutationTome> getRecipeClass() {
        return RecipeWrapperTransmutationTome.class;
    }

    @Override
    public String getRecipeCategoryUid(RecipeWrapperTransmutationTome recipe) {
        return ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(RecipeWrapperTransmutationTome recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(RecipeWrapperTransmutationTome recipe) {
        return recipe.recipe.getBase().size()> 0 && recipe.recipe.getTransmutator().size()> 0 && recipe.recipe.getResult().size() > 0;
    }

}
