package convenientadditions.api;

import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.api.util.EnchantmentUtil;

public class ConAddAPI {
	public static final String NAME="ConAddAPI";
	
	public static void init(){
		EnchantmentUtil.init();
		ItemNetworkProvider.init();
	}
}
