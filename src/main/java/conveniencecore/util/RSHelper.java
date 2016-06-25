package conveniencecore.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class RSHelper {
    public static int getStrongPower(IBlockAccess acc, BlockPos pos)
    {
        int i = 0;
        i = Math.max(i, acc.getStrongPower(pos.down(), EnumFacing.DOWN));

        if (i >= 15)
        {
            return i;
        }
        else
        {
            i = Math.max(i, acc.getStrongPower(pos.up(), EnumFacing.UP));

            if (i >= 15)
            {
                return i;
            }
            else
            {
                i = Math.max(i, acc.getStrongPower(pos.north(), EnumFacing.NORTH));

                if (i >= 15)
                {
                    return i;
                }
                else
                {
                    i = Math.max(i, acc.getStrongPower(pos.south(), EnumFacing.SOUTH));

                    if (i >= 15)
                    {
                        return i;
                    }
                    else
                    {
                        i = Math.max(i, acc.getStrongPower(pos.west(), EnumFacing.WEST));

                        if (i >= 15)
                        {
                            return i;
                        }
                        else
                        {
                            i = Math.max(i, acc.getStrongPower(pos.east(), EnumFacing.EAST));
                            return i >= 15 ? i : i;
                        }
                    }
                }
            }
        }
    }

    public static int getRedstonePower(IBlockAccess acc, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = acc.getBlockState(pos);
        return iblockstate.getBlock().shouldCheckWeakPower(iblockstate, acc, pos, facing) ? getStrongPower(acc, pos) : iblockstate.getWeakPower(acc, pos, facing);
    }
	
	public static int getIndirectPower(IBlockAccess acc, BlockPos pos){

        int i = 0;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            int j = getRedstonePower(acc, pos.offset(enumfacing), enumfacing);

            if (j >= 15)
            {
                return 15;
            }

            if (j > i)
            {
                i = j;
            }
        }

        return i;
	}
	
	public static boolean getIndirectlyPowered(IBlockAccess acc, BlockPos pos){
		return getIndirectPower(acc, pos)>0;
	}
}
