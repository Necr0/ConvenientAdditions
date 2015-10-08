package cub3d.api.matchers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TypeMatcher implements IItemMatcher {
	private Item item;
	
	@Override
	public boolean matches(ItemStack itemStack) {
		return itemStack.getItem()==item;
	}
	
	public TypeMatcher(Item i){
		this.item=i;
	}

	public TypeMatcher(ItemStack i){
		this.item=i.getItem();
	}
}
