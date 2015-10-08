package cub3d.api.matchers;

import net.minecraft.item.ItemStack;

public interface IItemMatcher {
	public boolean matches(ItemStack itemStack);
}