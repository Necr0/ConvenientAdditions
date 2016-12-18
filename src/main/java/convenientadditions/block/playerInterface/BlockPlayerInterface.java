package convenientadditions.block.playerInterface;

import convenientadditions.ModConstants;
import convenientadditions.base.CABlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlayerInterface extends CABlockContainer {
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockPlayerInterface() {
        super(ModConstants.BlockNames.playerInterfaceBlockName,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, false));
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityPlayerInterface();
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
        TileEntity t = world.getTileEntity(pos);
        if (t != null && t instanceof TileEntityPlayerInterface)
            return ((TileEntityPlayerInterface) t).hasTarget() ? 15 : 0;
        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity t = worldIn.getTileEntity(pos);
        if (t != null && t instanceof TileEntityPlayerInterface)
            return state.withProperty(ACTIVE, ((TileEntityPlayerInterface) t).hasTarget());
        return state.withProperty(ACTIVE, false);
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ACTIVE});
    }
}
