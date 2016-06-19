package conveniencecore.entity.behaviour.behaviours;

import conveniencecore.entity.behaviour.IEntitySpecialItemBehaviour;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;

public class BehaviourImmunityExplosion implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		if (source.isExplosion())
	    {
	        return false;
	    }
		return true;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {}

}
