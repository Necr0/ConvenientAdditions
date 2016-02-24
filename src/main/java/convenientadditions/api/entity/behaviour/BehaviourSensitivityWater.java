package convenientadditions.api.entity.behaviour;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;

public class BehaviourSensitivityWater implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		return false;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		if(item.handleWaterMovement())
    		item.attackEntityFrom(DamageSource.drown, .5f);
	}

}
