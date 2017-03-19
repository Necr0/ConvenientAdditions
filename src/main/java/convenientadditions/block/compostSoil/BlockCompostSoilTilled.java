package convenientadditions.block.compostSoil;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import convenientadditions.base.block.CABlock;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockCompostSoilTilled extends CABlock {
    protected static final AxisAlignedBB FARMLAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

    public BlockCompostSoilTilled() {
        super(ModConstants.BlockNames.compostSoilTilled, Material.GROUND);
        this.setTickRandomly(true).setHardness(0.5F).setCreativeTab(null);
        this.setSoundType(SoundType.GROUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockCompostSoil.DEGRADATION, 0));
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plantable) {
        BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        EnumPlantType plantType = plantable.getPlantType(world, plantPos);
        System.out.println(plantType.toString());
        return plantType == EnumPlantType.Crop || ModBlocks.compostSoilBlock.canSustainPlant(state,world,pos,side,plantable);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random r) {
        if (!world.isRemote) {
            BlockPos posU = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            Block b = world.getBlockState(posU).getBlock();
            IBlockState newB = world.getBlockState(posU);
            int deg = state.getValue(BlockCompostSoil.DEGRADATION);
            if (b instanceof IPlantable || b instanceof IGrowable) {
                b.updateTick(world, posU, world.getBlockState(posU), r);
                int i = deg;
                if (r.nextInt(23) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
                i++;
                if (r.nextInt(23) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
                i++;
                if (r.nextInt(23) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
            }
            if (r.nextInt(4) == 0) {
                if (deg < 10)
                    world.setBlockState(pos, state.withProperty(BlockCompostSoil.DEGRADATION, deg + 1));
                else
                    world.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
            }
            if (newB.getMaterial().isSolid())
                world.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState().withProperty(BlockCompostSoil.DEGRADATION, deg), 2);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random r, int p_149650_3_) {
        return ItemBlock.getItemFromBlock(ModBlocks.compostSoilBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(ModBlocks.compostSoilBlock);
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float height) {
        if (!world.isRemote && entity.canTrample(world, this, pos, height)) {
            if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing")) {
                return;
            }
            world.setBlockState(pos, ModBlocks.compostSoilBlock.getStateFromMeta(this.getMetaFromState(world.getBlockState(pos))));
            AxisAlignedBB axisalignedbb = ModBlocks.compostSoilBlock.getDefaultState().getCollisionBoundingBox(world, pos).offset(pos);

            for (Entity e : world.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb))
            {
                e.setPosition(entity.posX, axisalignedbb.maxY, entity.posZ);
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return FARMLAND_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFertile(World world, BlockPos pos) {
        return true;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    //
    // BLOCKSTATE STUFF
    //
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BlockCompostSoil.DEGRADATION, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BlockCompostSoil.DEGRADATION);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BlockCompostSoil.DEGRADATION);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(Helper.localize("tooltip.convenientadditions:compostDegraded" + stack.getItemDamage()));
    }
}
