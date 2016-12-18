package convenientadditions.compat.jei;

import convenientadditions.ModConstants;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;
import convenientadditions.compat.jei.transmutationTome.RecipeCategoryTransmutationTome;
import convenientadditions.compat.jei.transmutationTome.RecipeGeneratorTransmutationTome;
import convenientadditions.compat.jei.transmutationTome.RecipeHandlerTransmutationTome;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.item.transmutationTome.ContainerTransmutationTome;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class ConvAddJEIPlugin extends BlankModPlugin {
    public static IJeiHelpers jeiHelper;

    @Override
    public void register(@Nonnull IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();

        registry.addRecipeCategories(new RecipeCategoryTransmutationTome());
        registry.addRecipeHandlers(new RecipeHandlerTransmutationTome());
        registry.addRecipes(RecipeGeneratorTransmutationTome.getRecipes(TransmutationTomeRecipeHandler.INSTANCE));

        registry.addRecipeCategoryCraftingItem(new ItemStack(ModItems.itemTransmutationTome), ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory);

        //registry.addRecipeClickArea(GuiTransmutationTome.class, 152, 2, 13, 83, ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory);

        registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerTransmutationTome.class, ModConstants.Mod.MODID + ":" + ModConstants.Compat.JEI.transmutationTomeCategory, 0, 2, 3, 36);

        jeiHelper.getItemBlacklist().addItemToBlacklist(new ItemStack(ModBlocks.tempLightBlock));
    }
}
