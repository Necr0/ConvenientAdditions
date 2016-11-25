package convenientadditions.block.playerInterface;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.List;

public class TileEntityPlayerInterface extends TileEntity implements ICapabilityProvider, ITickable {//IInventory {

    public boolean hasPlayer = false;

    @Override
    public void update() {
        if (this.worldObj.isRemote) {
            if (hasTarget() != hasPlayer) {
                hasPlayer = hasTarget();
                this.getWorld().markBlockRangeForRenderUpdate(pos,pos);
            }
        }
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        EntityPlayer p = getPlayer();
        if (p != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return p.hasCapability(capability, EnumFacing.DOWN);
        else
            return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        EntityPlayer p = getPlayer();
        if (p != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return p.getCapability(capability, EnumFacing.DOWN);
        else
            return super.getCapability(capability, facing);
    }

    public EntityPlayer getPlayer() {
        List<EntityPlayer> l = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())));
        if (l.size() > 0)
            return l.get(0);
        else
            return null;
    }

    public boolean hasTarget() {
        return this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1)).size() > 0;
    }
}
