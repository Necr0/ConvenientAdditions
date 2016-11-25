package convenientadditions.compat.jei.transmutationTome;

import convenientadditions.api.registry.transmutationTome.ITransmutationTomeJEIRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecipeWrapperTransmutationTome extends BlankRecipeWrapper {
    public ITransmutationTomeJEIRecipe recipe;

    public RecipeWrapperTransmutationTome(ITransmutationTomeJEIRecipe r) {
        recipe = r;
    }

    @Override
    @Deprecated
    public List getInputs() {
        return Arrays.asList(recipe.getBase(), recipe.getTransmutator());
    }

    @Override
    @Deprecated
    public List getOutputs() {
        return Collections.singletonList(recipe.getResult());
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.currentScreen.drawCenteredString(minecraft.fontRendererObj, "" + recipe.getLevel(), 60, 15, 0x009900);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, Arrays.asList(recipe.getBase(), recipe.getTransmutator()));
        ingredients.setOutputs(ItemStack.class, recipe.getResult());
    }

}
