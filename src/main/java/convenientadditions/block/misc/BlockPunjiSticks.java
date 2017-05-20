package convenientadditions.block.misc;

import convenientadditions.ModConstants;
import convenientadditions.base.block.CABlock;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

/**
 * Created by Necro on 5/16/2017.
 */
public class BlockPunjiSticks extends CABlock {
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final DamageSource DAMAGE_SOURCE=new DamageSource("punjisticks").setDamageBypassesArmor();
    public static final HashMap<EnumFacing,AxisAlignedBB> AABB_COLLISON=new HashMap<>();
    public static final HashMap<EnumFacing,AxisAlignedBB> AABB_DAMAGE=new HashMap<>();
    public static final HashMap<EnumFacing,AxisAlignedBB> AABB_SELECTED=new HashMap<>();

    public BlockPunjiSticks() {
        super(ModConstants.BlockNames.punjiSticks, Material.ROCK);
        this.setHardness(1.5f).setResistance(2f);

        AABB_DAMAGE.put(EnumFacing.UP,new AxisAlignedBB(0,0.03125,0,1,0.375,1));
        AABB_DAMAGE.put(EnumFacing.DOWN,AABB_DAMAGE.get(EnumFacing.UP).offset(0,1-0.375,0));
        AABB_DAMAGE.put(EnumFacing.SOUTH,new AxisAlignedBB(0,0,0.03125,1,1,0.375));
        AABB_DAMAGE.put(EnumFacing.NORTH,AABB_DAMAGE.get(EnumFacing.SOUTH).offset(0,0,1-0.375));
        AABB_DAMAGE.put(EnumFacing.EAST,new AxisAlignedBB(0.03125,0,0,0.375,1,1));
        AABB_DAMAGE.put(EnumFacing.WEST,AABB_DAMAGE.get(EnumFacing.EAST).offset(1-0.375,0,0));

        AABB_COLLISON.put(EnumFacing.UP,new AxisAlignedBB(0,0,0,1,0.03125,1));
        AABB_COLLISON.put(EnumFacing.DOWN,AABB_COLLISON.get(EnumFacing.UP).offset(0,1-0.03125,0));
        AABB_COLLISON.put(EnumFacing.SOUTH,new AxisAlignedBB(0,0,0,1,1,0.03125));
        AABB_COLLISON.put(EnumFacing.NORTH,AABB_COLLISON.get(EnumFacing.SOUTH).offset(0,0,1-0.03125));
        AABB_COLLISON.put(EnumFacing.EAST,new AxisAlignedBB(0,0,0,0.03125,1,1));
        AABB_COLLISON.put(EnumFacing.WEST,AABB_COLLISON.get(EnumFacing.EAST).offset(1-0.03125,0,0));

        AABB_SELECTED.put(EnumFacing.UP,AABB_COLLISON.get(EnumFacing.UP).union(AABB_DAMAGE.get(EnumFacing.UP)));
        AABB_SELECTED.put(EnumFacing.DOWN,AABB_COLLISON.get(EnumFacing.DOWN).union(AABB_DAMAGE.get(EnumFacing.DOWN)));
        AABB_SELECTED.put(EnumFacing.NORTH,AABB_COLLISON.get(EnumFacing.NORTH).union(AABB_DAMAGE.get(EnumFacing.NORTH)));
        AABB_SELECTED.put(EnumFacing.SOUTH,AABB_COLLISON.get(EnumFacing.SOUTH).union(AABB_DAMAGE.get(EnumFacing.SOUTH)));
        AABB_SELECTED.put(EnumFacing.EAST,AABB_COLLISON.get(EnumFacing.EAST).union(AABB_DAMAGE.get(EnumFacing.EAST)));
        AABB_SELECTED.put(EnumFacing.WEST,AABB_COLLISON.get(EnumFacing.WEST).union(AABB_DAMAGE.get(EnumFacing.WEST)));
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        EnumFacing facing=state.getValue(FACING);
        if(entityIn instanceof EntityLivingBase&&entityIn.getEntityBoundingBox().intersectsWith(AABB_DAMAGE.get(facing).offset(pos)))
            entityIn.attackEntityFrom(DAMAGE_SOURCE, 2.25F);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {

        return AABB_COLLISON.get(blockState.getValue(FACING));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return AABB_SELECTED.get(state.getValue(FACING)).offset(pos);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getStateFromMeta(meta).withProperty(FACING, facing);
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }
}
