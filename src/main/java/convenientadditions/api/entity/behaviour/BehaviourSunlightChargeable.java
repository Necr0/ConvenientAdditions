package convenientadditions.api.entity.behaviour;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import convenientadditions.api.item.ISunlightChargeable;
import convenientadditions.api.util.Helper;

public class BehaviourSunlightChargeable implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		return true;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		ItemStack s=item.getEntityItem();
		if(s!=null && s.getItem() instanceof ISunlightChargeable){
			ISunlightChargeable sitem=(ISunlightChargeable)(s.getItem());
			if(sitem.isSunlightChargeable(s, -5)){
				if(item.worldObj.isDaytime() && !item.worldObj.isRaining() && Helper.canEntitySeeSky(item)){
					sitem.chargeItem(s, sitem.getSunlightChargeRate(s, -5));
				}
			}
		}
	}

}
