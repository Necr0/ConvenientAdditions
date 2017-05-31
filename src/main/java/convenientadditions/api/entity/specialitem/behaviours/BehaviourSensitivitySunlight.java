package convenientadditions.api.entity.specialitem.behaviours;

import convenientadditions.api.util.Helper;
import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class BehaviourSensitivitySunlight implements IEntitySpecialItemBehaviour {

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) {
        return true;
    }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        World w=item.getEntityWorld();
        if(w.isRemote)
            return;

        if (w.isDaytime() && !w.isRaining() && Helper.canEntitySeeSky(item) && new Random().nextInt(15) == 0)
            item.setFire(5);
    }

}
