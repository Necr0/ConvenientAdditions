package convenientadditions.compat.jei.transmutationTome;

import convenientadditions.api.registry.transmutationTome.ITransmutationTomeJEIRecipe;
import convenientadditions.api.registry.transmutationTome.ITransmutationTomeRecipe;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;

import java.util.ArrayList;
import java.util.List;

public class RecipeGeneratorTransmutationTome {
    public static List<RecipeWrapperTransmutationTome> getRecipes(TransmutationTomeRecipeHandler handler) {
        ArrayList<RecipeWrapperTransmutationTome> l = new ArrayList<RecipeWrapperTransmutationTome>();

        for (ITransmutationTomeRecipe r : handler.recipes) {
            if (r instanceof ITransmutationTomeJEIRecipe) {
                l.add(new RecipeWrapperTransmutationTome((ITransmutationTomeJEIRecipe) r));
            }
        }

        return l;
    }
}
