package convenientadditions.item;

import convenientadditions.api.item.IFuelItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelItemFuelHandler implements IFuelHandler {
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() instanceof IFuelItem){
			IFuelItem ifuel=(IFuelItem)fuel.getItem();
			return ifuel.isFuelItem(fuel)?ifuel.getFuelTime(fuel):0;
		}
		return 0;
	}
	
}
