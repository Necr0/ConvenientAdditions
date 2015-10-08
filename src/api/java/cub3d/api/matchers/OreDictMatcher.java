package cub3d.api.matchers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictMatcher implements IItemMatcher {

	public String entry;
	
	public OreDictMatcher(String entry){
		this.entry = entry;
	}
	
	@Override
	public boolean matches(ItemStack itemStack) {
		return OreDictionary.doesOreNameExist(entry)&&OreDictionary.getOres(entry).contains(itemStack);
	}

}
