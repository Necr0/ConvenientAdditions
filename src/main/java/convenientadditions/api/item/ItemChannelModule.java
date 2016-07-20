package convenientadditions.api.item;

import conveniencecore.IMatcher;
import convenientadditions.ConvenientAdditions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemChannelModule extends Item {
	public ItemChannelModule(){
		super();
		this.setHasSubtypes(true)
			.setCreativeTab(ConvenientAdditions.CREATIVETAB)
			.setMaxStackSize(1);
	}

	public abstract boolean hasMatcher(ItemStack stack);
	public abstract IMatcher getMatcher(ItemStack stack);
	
	public boolean doesMatch(ItemStack stack,IMatcher matcher){
		return IMatcher.matches(((ItemChannelModule)stack.getItem()).getMatcher(stack),matcher);
	}

}
