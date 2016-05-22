package conveniencecore.behaviours;

import java.util.Random;

import convenientadditions.api.util.Helper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;

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
