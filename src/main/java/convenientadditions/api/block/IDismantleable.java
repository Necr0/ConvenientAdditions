package convenientadditions.api.block;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IDismantleable {
	public boolean canDismantle(EntityPlayer player, IBlockAccess access, BlockPos pos);
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, IBlockAccess access, BlockPos pos, boolean returnDrops);
}
