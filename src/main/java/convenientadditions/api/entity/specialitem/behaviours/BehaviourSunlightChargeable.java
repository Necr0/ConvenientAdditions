package convenientadditions.api.entity.specialitem.behaviours;

import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.charge.ISunlightChargeable;
import convenientadditions.api.util.Helper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class BehaviourSunlightChargeable implements IEntitySpecialItemBehaviour {

    public static long DISCRIMINATOR;

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) {
        return true;
    }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        ItemStack s = item.getEntityItem();
        if (!s.isEmpty() && s.getItem() instanceof ISunlightChargeable) {
            ISunlightChargeable sitem = (ISunlightChargeable) (s.getItem());
            if (sitem.isSunlightChargeable(s, SlotNotation.SLOT_GROUND)) {
                if (item.getEntityWorld().isDaytime() && !item.getEntityWorld().isRaining() && Helper.canEntitySeeSky(item)) {
                    sitem.chargeItem(s, sitem.getSunlightChargeRate(s, SlotNotation.SLOT_GROUND));
                }
            }
        }
    }
}
