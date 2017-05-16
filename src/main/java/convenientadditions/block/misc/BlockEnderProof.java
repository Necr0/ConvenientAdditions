package convenientadditions.block.misc;

import convenientadditions.ModConstants;
import convenientadditions.api.util.FloodFill;
import convenientadditions.base.block.CABlock;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockEnderProof extends CABlock {
    public BlockEnderProof() {
        this(ModConstants.BlockNames.enderProofBlock, Material.ROCK);
        this.setHardness(11.25F).setResistance(500.0F);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public BlockEnderProof(String name, Material material) {
        super(name, material);
        this.setCategory(EnumItemCategory.MISC);
    }

    @SubscribeEvent
    public void onEnderTeleport(EnderTeleportEvent e){
        Entity ent=e.getEntity();
        List<BlockPos> l=rayTraceBlocks(ent.world,new Vec3d(ent.posX,ent.posY,ent.posZ),new Vec3d(e.getTargetX(),e.getTargetY(),e.getTargetZ()),false,new FloodFill.BlockMatcher(3).set(BlockEnderProof.class));
        if(l.size()>0){
            e.setCanceled(true);
        }
    }

    public static List<BlockPos> rayTraceBlocks(World w, Vec3d vec31, Vec3d vec32, boolean ignoreBlockWithoutBoundingBox, @Nullable FloodFill.BlockMatcher matcher)
    {
        List<BlockPos> ret=new ArrayList<>();
        if (!Double.isNaN(vec31.xCoord) && !Double.isNaN(vec31.yCoord) && !Double.isNaN(vec31.zCoord))
        {
            if (!Double.isNaN(vec32.xCoord) && !Double.isNaN(vec32.yCoord) && !Double.isNaN(vec32.zCoord)) {
                int i = MathHelper.floor(vec32.xCoord);
                int j = MathHelper.floor(vec32.yCoord);
                int k = MathHelper.floor(vec32.zCoord);
                int l = MathHelper.floor(vec31.xCoord);
                int i1 = MathHelper.floor(vec31.yCoord);
                int j1 = MathHelper.floor(vec31.zCoord);
                BlockPos blockpos = new BlockPos(l, i1, j1);
                IBlockState iblockstate = w.getBlockState(blockpos);

                if (!ret.contains(blockpos) && matcher.matches(iblockstate)) {
                    Block block = iblockstate.getBlock();
                    if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox(w, blockpos) != Block.NULL_AABB) && block.canCollideCheck(iblockstate, true)) {
                        RayTraceResult raytraceresult = iblockstate.collisionRayTrace(w, blockpos, vec31, vec32);

                        if (raytraceresult != null) {
                            ret.add(blockpos);
                        }
                    }
                }

                int k1 = 200;

                while (k1-- >= 0)
                {
                    if (Double.isNaN(vec31.xCoord) || Double.isNaN(vec31.yCoord) || Double.isNaN(vec31.zCoord))
                    {
                        return ret;
                    }

                    if (l == i && i1 == j && j1 == k)
                    {
                        return ret;
                    }

                    boolean flag2 = true;
                    boolean flag = true;
                    boolean flag1 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l)
                    {
                        d0 = (double)l + 1.0D;
                    }
                    else if (i < l)
                    {
                        d0 = (double)l + 0.0D;
                    }
                    else
                    {
                        flag2 = false;
                    }

                    if (j > i1)
                    {
                        d1 = (double)i1 + 1.0D;
                    }
                    else if (j < i1)
                    {
                        d1 = (double)i1 + 0.0D;
                    }
                    else
                    {
                        flag = false;
                    }

                    if (k > j1)
                    {
                        d2 = (double)j1 + 1.0D;
                    }
                    else if (k < j1)
                    {
                        d2 = (double)j1 + 0.0D;
                    }
                    else
                    {
                        flag1 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = vec32.xCoord - vec31.xCoord;
                    double d7 = vec32.yCoord - vec31.yCoord;
                    double d8 = vec32.zCoord - vec31.zCoord;

                    if (flag2)
                    {
                        d3 = (d0 - vec31.xCoord) / d6;
                    }

                    if (flag)
                    {
                        d4 = (d1 - vec31.yCoord) / d7;
                    }

                    if (flag1)
                    {
                        d5 = (d2 - vec31.zCoord) / d8;
                    }

                    if (d3 == -0.0D)
                    {
                        d3 = -1.0E-4D;
                    }

                    if (d4 == -0.0D)
                    {
                        d4 = -1.0E-4D;
                    }

                    if (d5 == -0.0D)
                    {
                        d5 = -1.0E-4D;
                    }

                    EnumFacing enumfacing;

                    if (d3 < d4 && d3 < d5)
                    {
                        enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
                        vec31 = new Vec3d(d0, vec31.yCoord + d7 * d3, vec31.zCoord + d8 * d3);
                    }
                    else if (d4 < d5)
                    {
                        enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
                        vec31 = new Vec3d(vec31.xCoord + d6 * d4, d1, vec31.zCoord + d8 * d4);
                    }
                    else
                    {
                        enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                        vec31 = new Vec3d(vec31.xCoord + d6 * d5, vec31.yCoord + d7 * d5, d2);
                    }

                    l = MathHelper.floor(vec31.xCoord) - (enumfacing == EnumFacing.EAST ? 1 : 0);
                    i1 = MathHelper.floor(vec31.yCoord) - (enumfacing == EnumFacing.UP ? 1 : 0);
                    j1 = MathHelper.floor(vec31.zCoord) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
                    blockpos = new BlockPos(l, i1, j1);
                    IBlockState iblockstate1 = w.getBlockState(blockpos);

                    if (!ret.contains(blockpos) && matcher.matches(iblockstate1)) {
                        Block block1 = iblockstate1.getBlock();
                        if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox(w, blockpos) != Block.NULL_AABB) && block1.canCollideCheck(iblockstate, true)) {
                            RayTraceResult raytraceresult = iblockstate1.collisionRayTrace(w, blockpos, vec31, vec32);

                            if (raytraceresult != null) {
                                ret.add(blockpos);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return blockMaterial!=Material.GLASS;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return blockMaterial!=Material.GLASS;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return blockMaterial!=Material.GLASS?super.getBlockLayer():BlockRenderLayer.CUTOUT;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        return !(iblockstate==blockState && blockMaterial == Material.GLASS) && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}
