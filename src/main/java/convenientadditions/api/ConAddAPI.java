package convenientadditions.api;

import convenientadditions.api.entity.behaviour.BehaviourSunlightChargeable;
import convenientadditions.api.item.charge.ChargeTickHandler;
import convenientadditions.api.registry.compost.CompostRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.api.util.EnchantmentUtil;

public class ConAddAPI {
	public static final String NAME="ConAddAPI";
	
	public static void init(){
		EnchantmentUtil.init();
		ChargeTickHandler.init();
		CompostRegistry.init();
		SeedBoxItemBehaviourRegistry.init();
		BehaviourSunlightChargeable.init();
	}
}
