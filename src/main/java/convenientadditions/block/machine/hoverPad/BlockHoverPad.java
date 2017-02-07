package convenientadditions.block.machine.hoverPad;

import convenientadditions.ModConstants;
import convenientadditions.base.CABlockMachine;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHoverPad extends CABlockMachine {
    public static PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockHoverPad() {
        super(ModConstants.BlockNames.hoverPad,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE,false));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityHoverPad();
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos from) {
        world.markBlockRangeForRenderUpdate(pos,pos);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(ACTIVE, worldIn.getTileEntity(pos).getWorld().isBlockIndirectlyGettingPowered(pos)>0);
    }

    @Override
    public int getMetaFromState(IBlockState state){return 0; }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ACTIVE);
    }
}
