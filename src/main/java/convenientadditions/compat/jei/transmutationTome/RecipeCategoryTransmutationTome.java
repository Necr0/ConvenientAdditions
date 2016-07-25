package convenientadditions.compat.jei.transmutationTome;

import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.compat.jei.ConvAddJEIPlugin;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class RecipeCategoryTransmutationTome extends BlankRecipeCategory<RecipeWrapperTransmutationTome> {
    private static final ResourceLocation jeiTomeGuiTextures = new ResourceLocation(ConvenientAdditions.MODID+":textures/gui/jei/transmutationTome.png");
    private static final IDrawable background=ConvAddJEIPlugin.jeiHelper.getGuiHelper().createDrawable(jeiTomeGuiTextures, 0, 0, 115, 56);
    
	@Override
	public String getUid() {
		return ConvenientAdditions.MODID+":"+Reference.jeiTransmutationTomeCategory;
	}

	@Override
	public String getTitle() {
	    return Helper.localize("jei."+ConvenientAdditions.MODID+":"+Reference.jeiTransmutationTomeCategory+".name");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RecipeWrapperTransmutationTome recipeWrapper) {
		recipeLayout.getItemStacks().init(0, true, 7, 18);
		recipeLayout.getItemStacks().init(1, true, 31, 18);
		recipeLayout.getItemStacks().init(2, false, 77, 18);
		if(recipeWrapper instanceof RecipeWrapperTransmutationTome){
			RecipeWrapperTransmutationTome w=(RecipeWrapperTransmutationTome)recipeWrapper;
			recipeLayout.getItemStacks().set(0, w.getInputs().get(0));
			recipeLayout.getItemStacks().set(1, w.getInputs().get(1));
			recipeLayout.getItemStacks().set(2, w.getOutputs().get(0));
		}
	}
		
}