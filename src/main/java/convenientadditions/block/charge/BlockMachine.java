package convenientadditions.block.charge;

import java.util.ArrayList;

import cofh.api.block.IDismantleable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockMachine extends BlockContainer implements IDismantleable {

	public BlockMachine(Material p_i45386_1_) {
		super(p_i45386_1_);
	}

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer player,World world, int x, int y, int z, boolean returnDrops) {
		ItemStack stack=new ItemStack(this);
		world.spawnEntityInWorld(new EntityItem(world, x+.5, y+.5, z+.5, stack));
		ArrayList<ItemStack> arr=new ArrayList<ItemStack>();
		arr.add(stack);
		world.setBlockToAir(x, y, z);
		return arr;
	}

	@Override
	public boolean canDismantle(EntityPlayer player, World world, int x, int y,int z) {
		return true;
	}

}
