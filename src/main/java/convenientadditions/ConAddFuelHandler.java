package convenientadditions;

import convenientadditions.api.IFuelItem;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class ConAddFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() instanceof IFuelItem){
			IFuelItem ifuel=(IFuelItem)fuel.getItem();
			return ifuel.isFuelItem(fuel)?ifuel.getFuelTime(fuel):0;
		}
		return 0;
	}
	
}
