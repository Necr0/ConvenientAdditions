package convenientadditions.block.technical;

import convenientadditions.ModConstants;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockTempLight extends Block {

    public BlockTempLight(float intensisty) {
        super(Material.FIRE);
        this.setRegistryName(ModConstants.BlockNames.tempLightBlockName).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.tempLightBlockName)
                .setLightLevel(intensisty).setTickRandomly(false);
        this.disableStats();
        this.translucent = true;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random r) {
        this.setTickRandomly(false);
        if(!world.getEntitiesWithinAABB(EntityPlayer.class,new AxisAlignedBB(pos.add(-4,-4,-4),pos.add(4,4,4))).isEmpty()) {
            if(r.nextInt(6)!=0){
                world.scheduleBlockUpdate(pos, ModBlocks.tempLightBlock, 80, 0);
                return;
            }
        }
        world.setBlockToAir(pos);
    }

    @Override
    public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return false;
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    }

    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
