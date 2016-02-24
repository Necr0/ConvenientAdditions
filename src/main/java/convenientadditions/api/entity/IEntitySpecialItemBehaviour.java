package convenientadditions.api.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;

public interface IEntitySpecialItemBehaviour {
	public void onCreate(EntityItem item);
	public boolean onAttackItemEntityFrom(EntityItem item,DamageSource source, float damage);
    public void onItemEntityUpdate(EntityItem item);
}
