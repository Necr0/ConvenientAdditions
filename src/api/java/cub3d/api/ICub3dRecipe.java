package cub3d.api;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public interface ICub3dRecipe {
	public boolean matches(ItemStack[][][] input, World world);
	public ItemStack getCraftingResult(ItemStack[][][] input);
	ItemStack getRecipeOutput();
}
