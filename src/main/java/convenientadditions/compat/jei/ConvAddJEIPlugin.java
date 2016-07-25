package convenientadditions.compat.jei;

import javax.annotation.Nonnull;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;
import convenientadditions.compat.jei.transmutationTome.RecipeCategoryTransmutationTome;
import convenientadditions.compat.jei.transmutationTome.RecipeGeneratorTransmutationTome;
import convenientadditions.compat.jei.transmutationTome.RecipeHandlerTransmutationTome;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class ConvAddJEIPlugin extends BlankModPlugin {
    public static IJeiHelpers jeiHelper;
	
	@Override
	public void register(@Nonnull IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();
        
		registry.addRecipeCategories(new RecipeCategoryTransmutationTome());
		registry.addRecipeHandlers(new RecipeHandlerTransmutationTome());
		registry.addRecipes(RecipeGeneratorTransmutationTome.getRecipes(TransmutationTomeRecipeHandler.INSTANCE));
		
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModItems.itemTransmutationTome), ConvenientAdditions.MODID+":"+Reference.jeiTransmutationTomeCategory);
		
		jeiHelper.getItemBlacklist().addItemToBlacklist(new ItemStack(ModBlocks.tempLightBlock));
	}
}
