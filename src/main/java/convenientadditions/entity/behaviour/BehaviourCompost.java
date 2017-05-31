package convenientadditions.entity.behaviour;

import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import convenientadditions.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BehaviourCompost implements IEntitySpecialItemBehaviour {

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
        if(w.isRemote || !item.onGround)
            return;

        BlockPos pos=new BlockPos(item);
        ItemStack stack=item.getEntityItem();
        IBlockState state=w.getBlockState(pos);
        if(!ModItems.itemCompost.tryCompostApply(stack,w,pos)&&!state.getMaterial().isSolid())
            ModItems.itemCompost.tryCompostApply(stack,w,pos.down());
        if(stack.isEmpty())
            item.setDead();
    }

}
