package convenientadditions.block.machine.playerInterface;

import convenientadditions.ModConstants;
import convenientadditions.base.block.block.CABlockMachine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPlayerInterface extends CABlockMachine {
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockPlayerInterface() {
        super(ModConstants.BlockNames.playerInterface,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, false));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
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
