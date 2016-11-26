package convenientadditions.entity.behaviour;

import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BehaviourAutoFertilizer implements IEntitySpecialItemBehaviour {

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) { return true; }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        World w = item.getEntityWorld();
        BlockPos pos = new BlockPos(MathHelper.floor(item.posX), MathHelper.floor(item.posY), MathHelper.floor(item.posZ));
        tryApply(item.getEntityItem(),w,pos);
        if(!item.getEntityItem().isEmpty() && item.onGround){
            pos=pos.down();
            tryApply(item.getEntityItem(),w,pos);
        }
    }

    public void tryApply(ItemStack s,World w,BlockPos p){
        ItemStack copy=s.copy();
        for(int i=0;i<2+w.rand.nextInt(3);i++){
            ItemDye.applyBonemeal(copy,w,p);
        }
        if(copy.getCount()<s.getCount()){
            s.shrink(1);
        }
    }
}
