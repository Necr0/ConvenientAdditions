package convenientadditions.entity.behaviour;

import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import convenientadditions.config.ModConfigMisc;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.Arrays;
import java.util.List;

public class BehaviourAutoFeed implements IEntitySpecialItemBehaviour {

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) {
        return true;
    }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        World w = item.getEntityWorld();
        if(w.isRemote)
            return;

        List<EntityAnimal> l=w.getEntitiesWithinAABB(EntityAnimal.class,new AxisAlignedBB(item.posX-1,item.posY-1,item.posZ-1,item.posX+1,item.posY+1,item.posZ+1));
        if(!item.getEntityItem().isEmpty()){
            for (EntityAnimal a:l) {
                if(Arrays.asList(ModConfigMisc.seedBox_autoFeedBlacklist).contains(EntityRegistry.getEntry(a.getClass()).getRegistryName().toString()))
                    continue;

                if(a.getGrowingAge() == 0 && !a.isInLove() && a.isBreedingItem(item.getEntityItem()) ){
                    item.getEntityItem().shrink(1);
                    a.setInLove(null);
                }else if(a.isChild() && a.isBreedingItem(item.getEntityItem())){
                    item.getEntityItem().shrink(1);
                    a.ageUp((int)((float)(-a.getGrowingAge() / 20) * 0.1F), true);
                }
            }
        }

    }

}

