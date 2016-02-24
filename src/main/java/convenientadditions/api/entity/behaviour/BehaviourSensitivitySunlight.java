package convenientadditions.api.entity.behaviour;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;
import convenientadditions.api.util.Helper;

public class BehaviourSensitivitySunlight implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		return true;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		if(item.worldObj.isDaytime() && !item.worldObj.isRaining() && Helper.canEntitySeeSky(item)&&new Random().nextInt(15)==0)
    		item.setFire(5);
	}

}
