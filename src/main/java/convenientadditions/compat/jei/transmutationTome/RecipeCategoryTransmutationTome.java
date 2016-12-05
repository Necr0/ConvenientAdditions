package convenientadditions.compat.jei.transmutationTome;

import convenientadditions.api.util.Helper;
import convenientadditions.ModConstants;
import convenientadditions.compat.jei.ConvAddJEIPlugin;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RecipeCategoryTransmutationTome extends BlankRecipeCategory<RecipeWrapperTransmutationTome> {
    private static final ResourceLocation jeiTomeGuiTextures = new ResourceLocation(ModConstants.Mod.MODID + ":textures/gui/jei/transmutationTome.png");
    private static final IDrawable background = ConvAddJEIPlugin.jeiHelper.getGuiHelper().createDrawable(jeiTomeGuiTextures, 0, 0, 115, 56);

    @Override
    public String getUid() {
        return ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory;
    }

    @Override
    public String getTitle() {
        return Helper.localize("jei." + ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory + ".name");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RecipeWrapperTransmutationTome recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 7, 18);
        recipeLayout.getItemStacks().init(1, true, 31, 18);
        recipeLayout.getItemStacks().init(2, false, 77, 18);
        recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0));
        recipeLayout.getItemStacks().set(1, ingredients.getInputs(ItemStack.class).get(1));
        recipeLayout.getItemStacks().set(2, ingredients.getOutputs(ItemStack.class).get(0));
    }

}
