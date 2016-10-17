package convenientadditions.compat.jei.transmutationTome;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import convenientadditions.api.registry.transmutationTome.ITransmutationTomeJEIRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class RecipeWrapperTransmutationTome extends BlankRecipeWrapper {
	public ITransmutationTomeJEIRecipe recipe;
	
	public RecipeWrapperTransmutationTome(ITransmutationTomeJEIRecipe r){
		recipe=r;
	}
	
	/**
	 * Return a list of recipe inputs.
	 * Each element can be an ItemStack, null, or a List of ItemStacks.
	 */
	@Override
	public List<List<ItemStack>> getInputs(){
		return Arrays.asList(recipe.getBase(),recipe.getTransmutator());
	}

	/**
	 * Return a list of recipe inputs.
	 * Each element can be an ItemStack, null, or a List of ItemStacks.
	 */
	@Override
	public List<List<ItemStack>> getOutputs(){
		return Collections.singletonList(recipe.getResult());
	}
	
	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.currentScreen.drawCenteredString(minecraft.fontRendererObj,""+recipe.getLevel(),60,15,0x009900);
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		
	}

}
