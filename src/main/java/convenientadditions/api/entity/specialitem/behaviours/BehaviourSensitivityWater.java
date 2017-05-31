package convenientadditions.api.entity.specialitem.behaviours;

import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.Random;

public class BehaviourSensitivityWater implements IEntitySpecialItemBehaviour {

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

        Random rnd = new Random();
        if (item.handleWaterMovement()) {
            if (rnd.nextInt(3) == 0) {
                w.spawnParticle(EnumParticleTypes.WATER_BUBBLE, item.posX, item.posY + .1d, item.posZ, 0, .25d, 0);
                if (rnd.nextBoolean())
                    item.attackEntityFrom(DamageSource.DROWN, 1f);
            }
        }
    }

}
