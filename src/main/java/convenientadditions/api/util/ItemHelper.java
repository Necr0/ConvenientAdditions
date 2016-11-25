package convenientadditions.api.util;

import java.util.Collection;

import net.minecraft.item.ItemStack;

public class ItemHelper {
	
	public static boolean match(ItemStack s1,ItemStack s2,boolean ignoreDamage,boolean ignoreNBT){
		if(s1==null||s2==null)
			return false;
		if(!ignoreDamage&&!ignoreNBT){
			return s1.isItemEqual(s2)&&ItemStack.areItemStackTagsEqual(s1, s2);
		}else if(ignoreDamage&&!ignoreNBT){
			return s1.getItem()==s2.getItem()&&ItemStack.areItemStackTagsEqual(s1, s2);
		}else if(!ignoreDamage&&ignoreNBT){
			return s1.isItemEqual(s2);
		}else if(ignoreDamage&&ignoreNBT){
			return s1.getItem()==s2.getItem();
		}
		return false;
	}
	
	public static boolean match(Collection<ItemStack> c,ItemStack s,boolean ignoreDamage,boolean ignoreNBT){
		for(ItemStack stack:c){
			if(match(stack, s, ignoreDamage, ignoreNBT))
				return true;
		}
		return false;
	}

}
