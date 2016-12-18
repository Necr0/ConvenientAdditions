package convenientadditions.block.storageMatrix;

import convenientadditions.ModConstants;
import convenientadditions.base.CABlockMachine;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockStorageMatrix extends CABlockMachine{

    public BlockStorageMatrix() {
        super(ModConstants.BlockNames.storageMatrixBlockName,Material.IRON);
        this.setHardness(4F).setResistance(8F);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityStorageMatrix();
    }
}
