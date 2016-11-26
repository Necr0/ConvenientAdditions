package convenientadditions.block.compostSoil;

import convenientadditions.ModConstants;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCompostSoilTilled extends BlockCompostSoil {

    public BlockCompostSoilTilled() {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.compostSoilTilledBlockName).setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(DEGRADATION, 0));
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plantable) {
        BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        EnumPlantType plantType = plantable.getPlantType(world, plantPos);

        if (plantType == EnumPlantType.Crop) return true;

        return super.canSustainPlant(state, world, pos, side, plantable);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random r) {
        if (!world.isRemote) {
            BlockPos posU = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            Block b = world.getBlockState(posU).getBlock();
            IBlockState newB = world.getBlockState(posU);
            int deg = state.getValue(DEGRADATION);
            if (b instanceof IPlantable || b instanceof IGrowable) {
                b.updateTick(world, posU, world.getBlockState(posU), r);
                int i = deg;
                if (r.nextInt(15) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
                i++;
                if (r.nextInt(15) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
                i++;
                if (r.nextInt(15) > i)
                    b.updateTick(world, posU, world.getBlockState(posU), r);
            }
            if (r.nextInt(4) == 0) {
                if (deg < 10)
                    world.setBlockState(pos, state.withProperty(DEGRADATION, deg + 1));
                else
                    world.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
            }
            if (newB.getMaterial().isSolid())
                world.setBlockState(pos, ModBlocks.compostSoilBlock.getDefaultState().withProperty(DEGRADATION, deg), 2);
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float height) {
        if (!world.isRemote && world.rand.nextFloat() < height - 0.5F) {
            if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing")) {
                return;
            }

            world.setBlockState(pos, ModBlocks.compostSoilBlock.getStateFromMeta(this.getMetaFromState(world.getBlockState(pos))), 3);
        }
    }

    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) (pos.getY() + 1), (double) (pos.getZ() + 1));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
