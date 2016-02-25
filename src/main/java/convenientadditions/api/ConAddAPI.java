package convenientadditions.api;

import convenientadditions.api.item.ChargeTickHandler;
import convenientadditions.api.item.FuelItemFuelHandler;
import convenientadditions.api.registry.BehaviourRegistry;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.api.util.EnchantmentUtil;

public class ConAddAPI {
	public static final String NAME="ConAddAPI";
	
	public static void init(){
		EnchantmentUtil.init();
		FuelItemFuelHandler.init();
		ChargeTickHandler.init();
		CompostRegistry.init();
		BehaviourRegistry.init();
		SeedBoxItemBehaviourRegistry.init();
	}
}
