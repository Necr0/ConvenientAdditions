package cub3d.api.matchers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DamageMatcher implements IItemMatcher {
	private Item item;
	private int damage;
	
	@Override
	public boolean matches(ItemStack itemStack) {
		return itemStack.getItem()==item&&itemStack.getItemDamage()==damage;
	}
	
	public DamageMatcher(Item item,int meta){
		this.item=item;
		this.damage=meta;
	}

	public DamageMatcher(ItemStack i){
		this.item=i.getItem();
		this.damage=i.getItemDamage();
	}
}
