package convenientadditions.api.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import convenientadditions.api.tileentity.IConfigurable;

public abstract class BlockConfigurable extends BlockContainer {
    protected BlockConfigurable(Material p_i45386_1_) {
		super(p_i45386_1_);
	}

	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
    {
    	TileEntity te=worldObj.getTileEntity(x, y, z);
        if(te!=null&&te instanceof IConfigurable){
        	return ((IConfigurable)te).configureSide(axis);
        }
        return super.rotateBlock(worldObj, x, y, z, axis);
    }
}
