package convenientadditions.api.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;

public interface IItemSpecialEntityItem {
    public boolean onAttackItemEntityFrom(EntityItem item,DamageSource source, float damage);
    public boolean onItemEntityUpdate(EntityItem item);
}
