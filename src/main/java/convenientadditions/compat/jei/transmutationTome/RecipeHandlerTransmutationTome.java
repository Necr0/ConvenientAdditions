package convenientadditions.compat.jei.transmutationTome;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class RecipeHandlerTransmutationTome implements IRecipeHandler<RecipeWrapperTransmutationTome> {

	@Override
	public Class<RecipeWrapperTransmutationTome> getRecipeClass() {
		return RecipeWrapperTransmutationTome.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return ConvenientAdditions.MODID+":"+Reference.jeiTransmutationTomeCategory;
	}

	@Override
	public String getRecipeCategoryUid(RecipeWrapperTransmutationTome recipe) {
		return ConvenientAdditions.MODID+":"+Reference.jeiTransmutationTomeCategory;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeWrapperTransmutationTome recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeWrapperTransmutationTome recipe) {
		return recipe.getInputs().size() > 1 && recipe.getOutputs().size() > 0;
	}
	
}
