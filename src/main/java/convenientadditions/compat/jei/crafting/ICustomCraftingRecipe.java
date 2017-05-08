package convenientadditions.compat.jei.crafting;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Necro on 5/6/2017.
 */
public interface ICustomCraftingRecipe {
    List<Object> getInputs();
    List<ItemStack> getResult();
    default boolean isShapeless(){return false;}
}
