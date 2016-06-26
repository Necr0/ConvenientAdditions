package conveniencecore.util;

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

}
