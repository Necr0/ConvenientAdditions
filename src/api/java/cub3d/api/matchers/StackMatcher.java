package cub3d.api.matchers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackMatcher implements IItemMatcher {
	private Item item;
	private int damage;
	private NBTTagCompound nbt;
	
	@Override
	public boolean matches(ItemStack itemStack) {
		if(itemStack.hasTagCompound())
			if(nbt!=null)
				return itemStack.getItem()==item&&itemStack.getTagCompound().equals(nbt)&&itemStack.getItemDamage()==damage;
			else
				return false;
		else
			return nbt==null&&itemStack.getItem()==item&&itemStack.getItemDamage()==damage;
	}

	public StackMatcher(Item item,int meta,NBTTagCompound nbt){
		this.item=item;
		this.damage=meta;
		this.nbt=nbt;
	}
	
	public StackMatcher(ItemStack i){
		this.item=i.getItem();
		this.damage=i.getItemDamage();
		this.nbt=i.getTagCompound();
	}
}
