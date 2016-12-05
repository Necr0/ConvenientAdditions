package convenientadditions.block.storageMatrix;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.block.BlockMachine;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockStorageMatrix extends BlockMachine{

    public BlockStorageMatrix() {
        super(Material.IRON);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.storageMatrixBlockName).setHardness(4F).setResistance(8F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityStorageMatrix();
    }
}
