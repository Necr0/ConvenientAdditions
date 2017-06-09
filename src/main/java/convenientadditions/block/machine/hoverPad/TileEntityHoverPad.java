package convenientadditions.block.machine.hoverPad;

import convenientadditions.api.util.Helper;
import convenientadditions.base.block.tileentity.CATileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class TileEntityHoverPad extends CATileEntity implements ITickable {

    @Override
    public void update() {
        double mult = getWorld().isBlockIndirectlyGettingPowered(pos) / 15d;
        if (mult == 0d)
            return;
        double max_acc=mult*.515d+.075d;
        double max_range=max_acc*80;
        List<EntityLivingBase> l = Helper.getEntitiesInAABBStrict(getWorld(),EntityLivingBase.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + checkForObstuctions(max_range), pos.getZ() + 1));
        for (EntityLivingBase e : l) {
            double acc=max_acc/Math.max(1,1+(.5d*(e.posY-pos.getY())));
            if (e instanceof EntityPlayer && e.isSneaking())
                e.addVelocity(0, acc/2.25d, 0);
            else
                e.addVelocity(0, acc, 0);
            if (e.motionY > -.666)
                e.fallDistance = 0;
        }
    }

    public int checkForObstuctions(double maxRange) {
        int i;
        for(i=0; i<Math.ceil(maxRange); i++){
            IBlockState s=getWorld().getBlockState(getPos().up(i+1));
            if(s.isSideSolid(getWorld(),getPos(), EnumFacing.DOWN)||s.isSideSolid(getWorld(),getPos(), EnumFacing.UP))
                break;
        }
        return i+1;
    }
}
