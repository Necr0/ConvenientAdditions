package convenientadditions.api.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockConfigurable extends BlockContainer {
    protected BlockConfigurable(Material p_i45386_1_) {
        super(p_i45386_1_);
    }

    @Override
    public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing axis) {
        TileEntity te = worldObj.getTileEntity(pos);
        if (te != null && te instanceof IConfigurable) {
            return ((IConfigurable) te).configureSide(axis);
        }
        return super.rotateBlock(worldObj, pos, axis);
    }
}
