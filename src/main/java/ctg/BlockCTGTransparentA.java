package ctg;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Necro on 6/19/2017.
 */
public class BlockCTGTransparentA extends Block {
    public static final PropertyConnectionsA CONNECTIONS=new PropertyConnectionsA("c");

    public BlockCTGTransparentA(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTIONS,"normal"));
    }

    public BlockCTGTransparentA(Material materialIn) {
        super(materialIn);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockAccess.getBlockState(pos.offset(side)).getBlock() != this && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.withProperty(CONNECTIONS,PropertyConnectionsA.fromBooleans(
                isSideConnected(state,world,pos,EnumFacing.UP),
                isSideConnected(state,world,pos,EnumFacing.DOWN),
                isSideConnected(state,world,pos,EnumFacing.NORTH),
                isSideConnected(state,world,pos,EnumFacing.EAST),
                isSideConnected(state,world,pos,EnumFacing.SOUTH),
                isSideConnected(state,world,pos,EnumFacing.WEST)
        ));
    }

    public boolean isSideConnected(IBlockState state, IBlockAccess world, BlockPos pos,EnumFacing f){
        return world.getBlockState(pos.offset(f)).getBlock()==state.getBlock();
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    public int getMetaFromState(IBlockState state){return 0;}
    protected BlockStateContainer createBlockState(){return new BlockStateContainer(this, CONNECTIONS);}
}
