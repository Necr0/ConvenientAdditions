package convenientadditions.api;

import convenientadditions.api.entity.behaviour.BehaviourSunlightChargeable;
import convenientadditions.api.item.charge.ChargeTickHandler;
import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.api.util.EnchantmentUtil;

public class ConAddAPI {
	public static final String NAME="ConAddAPI";
	
	public static void init(){
		EnchantmentUtil.init();
		ChargeTickHandler.init();
		BehaviourSunlightChargeable.init();
		ItemNetworkProvider.init();
	}
}
