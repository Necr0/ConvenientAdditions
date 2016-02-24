package convenientadditions.api;

import convenientadditions.api.item.ChargeTickHandler;
import convenientadditions.api.item.FuelItemFuelHandler;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.api.util.EnchantmentUtil;

public class ConAddAPI {
	public static void init(){
		EnchantmentUtil.init();
		FuelItemFuelHandler.init();
		ChargeTickHandler.init();
		CompostRegistry.init();
	}
}
