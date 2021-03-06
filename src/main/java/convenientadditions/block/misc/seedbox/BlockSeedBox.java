package convenientadditions.block.misc.seedbox;

import convenientadditions.ModConstants;
import convenientadditions.base.block.block.CABlockConfigurable;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSeedBox extends CABlockConfigurable {
    public static final PropertyBool OUTLET_TOP = PropertyBool.create("outlet_top");
    public static final PropertyBool OUTLET_BOTTOM = PropertyBool.create("outlet_bottom");
    public static final PropertyBool OUTLET_NORTH = PropertyBool.create("outlet_north");
    public static final PropertyBool OUTLET_EAST = PropertyBool.create("outlet_east");
    public static final PropertyBool OUTLET_SOUTH = PropertyBool.create("outlet_south");
    public static final PropertyBool OUTLET_WEST = PropertyBool.create("outlet_west");

    public BlockSeedBox() {
        super(ModConstants.BlockNames.seedBox,Material.WOOD);
        this.setHardness(2F).setResistance(3F);
        this.setSoundType(SoundType.WOOD).setCategory(EnumItemCategory.MISC).setDefaultAdditionalInfo(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OUTLET_TOP, false).withProperty(OUTLET_BOTTOM, true).withProperty(OUTLET_NORTH, false).withProperty(OUTLET_EAST, false).withProperty(OUTLET_SOUTH, false).withProperty(OUTLET_WEST, false));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntitySeedBox();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity t = worldIn.getTileEntity(pos);
        IBlockState ret = state;
        if (t != null && t instanceof TileEntitySeedBox) {
            TileEntitySeedBox s = (TileEntitySeedBox) t;
            ret = ret.withProperty(OUTLET_TOP, s.isOutput(EnumFacing.UP));
            ret = ret.withProperty(OUTLET_BOTTOM, s.isOutput(EnumFacing.DOWN));
            ret = ret.withProperty(OUTLET_NORTH, s.isOutput(EnumFacing.NORTH));
            ret = ret.withProperty(OUTLET_EAST, s.isOutput(EnumFacing.EAST));
            ret = ret.withProperty(OUTLET_SOUTH, s.isOutput(EnumFacing.SOUTH));
            ret = ret.withProperty(OUTLET_WEST, s.isOutput(EnumFacing.WEST));
        }
        return ret;
    }

    @Override
    public int getMetaFromState(IBlockState state){ return 0; }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, OUTLET_TOP, OUTLET_BOTTOM, OUTLET_NORTH, OUTLET_EAST, OUTLET_SOUTH, OUTLET_WEST);
    }
}
