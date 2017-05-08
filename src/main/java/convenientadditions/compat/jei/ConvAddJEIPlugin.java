package convenientadditions.compat.jei;

import convenientadditions.ModConstants;
import convenientadditions.api.registry.transmutationTome.ITransmutationTomeJEIRecipe;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;
import convenientadditions.compat.jei.crafting.ICustomCraftingRecipe;
import convenientadditions.compat.jei.crafting.RecipeHandlerCustomCrafting;
import convenientadditions.compat.jei.transmutationTome.RecipeCategoryTransmutationTome;
import convenientadditions.compat.jei.transmutationTome.RecipeGeneratorTransmutationTome;
import convenientadditions.compat.jei.transmutationTome.RecipeHandlerTransmutationTome;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.item.relic.transmutationTome.ContainerTransmutationTome;
import mezz.jei.api.*;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class ConvAddJEIPlugin extends BlankModPlugin {
    public static IJeiHelpers jeiHelper;
    public static ICraftingGridHelper craftingGridHelper;

    @Override
    public void register(@Nonnull IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();
        craftingGridHelper = jeiHelper.getGuiHelper().createCraftingGridHelper(1,0);

        registry.addRecipeCategories(RecipeCategoryTransmutationTome.INSTANCE);
        registry.handleRecipes(ITransmutationTomeJEIRecipe.class,new RecipeHandlerTransmutationTome(),RecipeCategoryTransmutationTome.INSTANCE.getUid());
        registry.addRecipes(RecipeGeneratorTransmutationTome.getRecipes(TransmutationTomeRecipeHandler.INSTANCE),RecipeCategoryTransmutationTome.INSTANCE.getUid());
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModItems.itemTransmutationTome), ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerTransmutationTome.class, ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory, 0, 2, 3, 36);

        jeiHelper.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(ModBlocks.tempLightBlock));


        registry.handleRecipes(ICustomCraftingRecipe.class,new RecipeHandlerCustomCrafting(), VanillaRecipeCategoryUid.CRAFTING);
        //registry.addRecipes(RecipeGeneratorTransmutationTome.getRecipes(TransmutationTomeRecipeHandler.INSTANCE),RecipeCategoryTransmutationTome.INSTANCE.getUid());
    }
}
