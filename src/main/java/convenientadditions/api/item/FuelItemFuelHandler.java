package convenientadditions.api.item;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelItemFuelHandler implements IFuelHandler {
	
	public static final FuelItemFuelHandler INSTANCE=new FuelItemFuelHandler();
	private static boolean registered=false;
	
	public static void init(){
		if(!registered){
	    	GameRegistry.registerFuelHandler(new FuelItemFuelHandler());
		}
	}
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() instanceof IFuelItem){
			IFuelItem ifuel=(IFuelItem)fuel.getItem();
			return ifuel.isFuelItem(fuel)?ifuel.getFuelTime(fuel):0;
		}
		return 0;
	}
	
}
