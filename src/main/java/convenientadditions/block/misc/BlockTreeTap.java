package convenientadditions.block.misc;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.block.block.CABlock;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.init.ModItems;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.AxisDirection;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockTreeTap extends CABlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<EnumBottleState> BOTTLE_STATE = PropertyEnum.create("bottle_state", EnumBottleState.class);

    public BlockTreeTap() {
        super(ModConstants.BlockNames.treetap,Material.WOOD);
        this.setHardness(2F).setResistance(3F).setTickRandomly(true);
        this.setSoundType(SoundType.WOOD).setCategory(EnumItemCategory.MISC);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BOTTLE_STATE, EnumBottleState.empty));
        this.setDefaultAdditionalInfo(true);
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if(facing.getAxis()!=Axis.Y)
            return this.getStateFromMeta(meta).withProperty(FACING, facing);
        for(EnumFacing f:EnumFacing.Plane.HORIZONTAL.facings()){
            if(Helper.doesOreDictMatch(worldIn.getBlockState(pos.offset(f)),"logWood",false))
                return this.getStateFromMeta(meta).withProperty(FACING, f);
        }
        for(EnumFacing f:EnumFacing.Plane.HORIZONTAL.facings()){
            if(worldIn.getBlockState(pos.offset(f)).isSideSolid(worldIn,pos.offset(f),f.getOpposite()))
                return this.getStateFromMeta(meta).withProperty(FACING, f);
        }
        return this.getStateFromMeta(meta).withProperty(FACING, EnumFacing.NORTH);
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;
        EnumBottleState s = state.getValue(BOTTLE_STATE);
        ItemStack held=player.getHeldItem(hand);
        if (s == EnumBottleState.empty) {
            if (held.getItem() == ModItems.itemSapBottle) {
                world.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.values()[held.getItemDamage() + 1]));
                player.setHeldItem(hand, ItemStack.EMPTY);
            }
        } else {
            world.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.empty));
            Helper.insertOrDrop(player,new ItemStack(ModItems.itemSapBottle, 1, s.ordinal() - 1));
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        EnumBottleState b=state.getValue(BOTTLE_STATE);
        if (b != EnumBottleState.empty) {
            float rx = world.rand.nextFloat() * 0.8F + 0.1F;
            float ry = world.rand.nextFloat() * 0.8F + 0.1F;
            float rz = world.rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem = new EntityItem(world, pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz, new ItemStack(ModItems.itemSapBottle, 1, b.ordinal() - 1));
            float factor = 0.05F;
            entityItem.motionX = world.rand.nextGaussian() * factor;
            entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
            entityItem.motionZ = world.rand.nextGaussian() * factor;
            world.spawnEntity(entityItem);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote && rand.nextInt(12) == 0) {
            IBlockState log = worldIn.getBlockState(pos.add(state.getValue(FACING).getDirectionVec()));
            if (Helper.doesOreDictMatch(log, "logWood", false)) {
                if (state.getValue(BOTTLE_STATE) == EnumBottleState.bottle)
                    worldIn.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.bottle_half));
                else if (state.getValue(BOTTLE_STATE) == EnumBottleState.bottle_half)
                    worldIn.setBlockState(pos, state.withProperty(BOTTLE_STATE, EnumBottleState.bottle_full));
            }
        }
    }


    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        EnumFacing f = state.getValue(FACING);
        boolean b = state.getValue(BOTTLE_STATE) != EnumBottleState.empty;
        new AxisAlignedBB(5 / 16d, 4 / 16d, .0, 11 / 16d, 14 / 16d, 10 / 16d);
        double side = b ? 5 / 16d : 6 / 16d;
        double front = b ? 10 / 16d : 5 / 16d;
        return new AxisAlignedBB(
                f.getAxis() == Axis.X ? (f.getAxisDirection() == AxisDirection.NEGATIVE ? 0d : 1 - front) : side,
                b ? 4 / 16d : 10 / 16d,
                f.getAxis() == Axis.Z ? (f.getAxisDirection() == AxisDirection.NEGATIVE ? 0d : 1 - front) : side,
                f.getAxis() == Axis.X ? (f.getAxisDirection() == AxisDirection.POSITIVE ? 1d : front) : 1 - side,
                14 / 16d,
                f.getAxis() == Axis.Z ? (f.getAxisDirection() == AxisDirection.POSITIVE ? 1d : front) : 1 - side);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing f = state.getValue(FACING);
        boolean b = state.getValue(BOTTLE_STATE) != EnumBottleState.empty;
        new AxisAlignedBB(5 / 16d, 4 / 16d, .0, 11 / 16d, 14 / 16d, 10 / 16d);
        double side = b ? 5 / 16d : 6 / 16d;
        double front = b ? 10 / 16d : 5 / 16d;
        return new AxisAlignedBB(
                f.getAxis() == Axis.X ? (f.getAxisDirection() == AxisDirection.NEGATIVE ? 0d : 1 - front) : side,
                b ? 4 / 16d : 10 / 16d,
                f.getAxis() == Axis.Z ? (f.getAxisDirection() == AxisDirection.NEGATIVE ? 0d : 1 - front) : side,
                f.getAxis() == Axis.X ? (f.getAxisDirection() == AxisDirection.POSITIVE ? 1d : front) : 1 - side,
                14 / 16d,
                f.getAxis() == Axis.Z ? (f.getAxisDirection() == AxisDirection.POSITIVE ? 1d : front) : 1 - side);
    }

    //RENDERING

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    //BLOCKSTATE

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(BOTTLE_STATE, EnumBottleState.values()[(meta & 12) >> 2]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(FACING)).getHorizontalIndex() +
                ((state.getValue(BOTTLE_STATE)).ordinal() << 2);
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BOTTLE_STATE);
    }

    public enum EnumBottleState implements IStringSerializable {
        empty,
        bottle,
        bottle_half,
        bottle_full;

        @Override
        public String getName() {
            return this.name();
        }
    }
}
