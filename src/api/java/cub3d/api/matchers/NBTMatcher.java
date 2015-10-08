package cub3d.api.matchers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTMatcher implements IItemMatcher {
	private Item item;
	private NBTTagCompound nbt;
	
	@Override
	public boolean matches(ItemStack itemStack) {
		if(itemStack.hasTagCompound())
			if(nbt!=null)
				return itemStack.getItem()==item&&itemStack.getTagCompound().equals(nbt);
			else
				return false;
		else
			return nbt==null&&itemStack.getItem()==item;
		
	}
	
	public NBTMatcher(Item item,NBTTagCompound nbt){
		this.item=item;
		this.nbt=nbt;
	}

	public NBTMatcher(ItemStack i){
		this.item=i.getItem();
		this.nbt=i.getTagCompound();
	}
}
