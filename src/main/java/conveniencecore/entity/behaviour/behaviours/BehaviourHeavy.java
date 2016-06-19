package conveniencecore.entity.behaviour.behaviours;

import conveniencecore.entity.behaviour.IEntitySpecialItemBehaviour;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;

public class BehaviourHeavy implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		return true;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		item.motionY-=.05f;
	}

}