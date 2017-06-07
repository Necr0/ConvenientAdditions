package convenientadditions.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Necro on 4/22/2017.
 */
public class FloodFill {

    //mode 0:shared face, 1:shared edge, 2: shared corner
    public static List<BlockPos> getAdjacentBlockPosList(BlockPos pos, int mode) {
        List<BlockPos> ret = new ArrayList<>();

        for (EnumFacing f : EnumFacing.VALUES) {
            ret.add(pos.add(f.getDirectionVec()));
        }
        if (mode > 0) {
            for (EnumFacing f : EnumFacing.HORIZONTALS) {
                ret.add(pos.up().offset(f));
                ret.add(pos.down().offset(f));
                ret.add(pos.offset(f).offset(f.rotateY()));
            }
        }
        if (mode > 1) {
            for (EnumFacing f : EnumFacing.HORIZONTALS) {
                ret.add(pos.up().offset(f).offset(f.rotateY()));
                ret.add(pos.down().offset(f).offset(f.rotateY()));
            }
        }

        return ret;
    }

    public static List<BlockPos> floodFill(World w, BlockPos pos, BlockMatcher m, int mode) {
        return floodFill(w, pos, m, mode, 64, false, true);
    }

    public static List<BlockPos> floodFill(World w, BlockPos pos, BlockMatcher m, int mode, int limit, boolean ignoreMeta, boolean ignoreFirst) {
        List<BlockPos> ret = new ArrayList<>();
        if (ignoreFirst) {
            for (BlockPos p : getAdjacentBlockPosList(pos, mode)) {
                floodFillRecursive(w, p, m, mode, limit, ignoreMeta, ret);
            }
        }
        return ret;
    }

    public static void floodFillRecursive(World w, BlockPos pos, BlockMatcher m, int mode, int limit, boolean ignoreMeta, List<BlockPos> markers) {
        if (m.matches(w.getBlockState(pos))) {
            if (!doesContainBlockPos(markers, pos)) {
                if (markers.size() < limit || limit == -1) {
                    markers.add(pos);
                    for (BlockPos p : getAdjacentBlockPosList(pos, mode)) {
                        floodFillRecursive(w, p, m, mode, limit, ignoreMeta, markers);
                    }
                }
            }
        }
    }

    public static boolean doesContainBlockPos(Collection<BlockPos> c, BlockPos pos) {
        for (BlockPos p : c) {
            if (pos.equals(p))
                return true;
        }
        return false;
    }

    public static class BlockMatcher{
        public String ore;
        public Block block;
        public IBlockState state;
        public Class<? extends Block> clazz;
        public int mode=0;//0=ore,1=Block,2=BlockState

        public BlockMatcher(int mode){
            this.mode=mode;
        }

        public boolean matches(IBlockState state){
            switch (mode){
                case 0:
                    return Helper.doesOreDictMatch(state,ore,false);
                case 1:
                    return state.getBlock()==block;
                case 2:
                    return state==this.state;
                case 3:
                    return clazz.isInstance(state.getBlock());
                default:
                    return false;
            }
        }

        public BlockMatcher set(Object obj){
            switch (mode){
                case 0:
                    ore=(String)obj;
                    break;
                case 1:
                    block=(Block)obj;
                    break;
                case 2:
                    state=(IBlockState)obj;
                    break;
                case 3:
                    clazz=(Class<? extends Block>) obj;
                    break;
            }
            return this;
        }
    }
}
