package convenientadditions.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Collection;

public class ItemHelper {
	
	public static boolean match(ItemStack s1,ItemStack s2,boolean ignoreDamage,boolean ignoreNBT){
		if(s1.isEmpty()||s2.isEmpty())
			return false;
		if(!ignoreDamage&&!ignoreNBT){
			return s1.isItemEqual(s2)&&ItemStack.areItemStackTagsEqual(s1, s2);
		}else if(ignoreDamage&&!ignoreNBT){
			return s1.getItem()==s2.getItem()&&ItemStack.areItemStackTagsEqual(s1, s2);
		}else if(!ignoreDamage){
			return s1.isItemEqual(s2);
		}else{
			return s1.getItem()==s2.getItem();
		}
	}
	
	public static boolean match(Collection<ItemStack> c,ItemStack s,boolean ignoreDamage,boolean ignoreNBT){
		for(ItemStack stack:c){
			if(match(stack, s, ignoreDamage, ignoreNBT))
				return true;
		}
		return false;
	}

	public static NonNullList<ItemStack> deepCopyItemList(NonNullList<ItemStack> list){
		NonNullList<ItemStack> ret=NonNullList.create();
		for (ItemStack s:list)
			ret.add(s.copy());
		return ret;
	}
}
