package convenientadditions.api.entity.specialitem;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;

public interface IEntitySpecialItemBehaviour {
    void onCreate(EntityItem item);

    boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage);

    void onItemEntityUpdate(EntityItem item);
}
