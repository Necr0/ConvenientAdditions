package convenientadditions.block.proximitySensor;

import convenientadditions.ModConstants;
import convenientadditions.base.CABlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockProximitySensor extends CABlockContainer {
    public static final PropertyInteger STRENGTH = PropertyInteger.create("strength", 0, 15);

    public BlockProximitySensor() {
        super(ModConstants.BlockNames.proximitySensorBlockName,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STRENGTH, 0));
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileEntityProximitySensor)
            return ((TileEntityProximitySensor) te).getComp();
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityProximitySensor();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity t = worldIn.getTileEntity(pos);
        if (t != null && t instanceof TileEntityProximitySensor)
            return state.withProperty(STRENGTH, ((TileEntityProximitySensor) t).getComp());
        return state.withProperty(STRENGTH, 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STRENGTH);
    }
}
