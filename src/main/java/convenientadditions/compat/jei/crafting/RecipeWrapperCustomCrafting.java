package convenientadditions.compat.jei.crafting;

import convenientadditions.compat.jei.ConvAddJEIPlugin;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.Collections;

/**
 * Created by Necro on 5/6/2017.
 */
public class RecipeWrapperCustomCrafting extends BlankRecipeWrapper implements ICustomCraftingRecipeWrapper {
    ICustomCraftingRecipe recipe;

    public RecipeWrapperCustomCrafting(ICustomCraftingRecipe recipe){
        this.recipe=recipe;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IIngredients ingredients) {
        if(recipe.isShapeless())
            recipeLayout.setShapeless();
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        ConvAddJEIPlugin.craftingGridHelper.setInputs(guiItemStacks, ingredients.getInputs(ItemStack.class));
        guiItemStacks.set(0, ingredients.getOutputs(ItemStack.class).get(0));
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class,ConvAddJEIPlugin.jeiHelper.getStackHelper().expandRecipeItemStackInputs(recipe.getInputs()));
        ingredients.setOutputLists(ItemStack.class,Collections.singletonList(recipe.getResult()));
    }
}
