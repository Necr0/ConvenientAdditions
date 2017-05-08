package convenientadditions.compat.jei.transmutationTome;

import convenientadditions.api.registry.transmutationTome.ITransmutationTomeJEIRecipe;
import convenientadditions.api.registry.transmutationTome.ITransmutationTomeRecipe;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;

import java.util.ArrayList;
import java.util.List;

public class RecipeGeneratorTransmutationTome {
    public static List<ITransmutationTomeJEIRecipe> getRecipes(TransmutationTomeRecipeHandler handler) {
        ArrayList<ITransmutationTomeJEIRecipe> l = new ArrayList<>();

        for (ITransmutationTomeRecipe r : handler.recipes) {
            if (r instanceof ITransmutationTomeJEIRecipe) {
                l.add((ITransmutationTomeJEIRecipe) r);
            }
        }

        return l;
    }
}
