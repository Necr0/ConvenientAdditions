package convenientadditions.api.entity.behaviour;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BehaviourAutoCrops implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		return true;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		World w=item.worldObj;
		int x=MathHelper.floor_double(item.posX),y=MathHelper.floor_double(item.posY)-1,z=MathHelper.floor_double(item.posZ);
		BlockPos pos=new BlockPos(x, y, z);
		if(item.onGround&&w.getBlockState(pos).getBlock().isAir(w, new BlockPos(x,y+1,z))){
			Item i=item.getEntityItem().getItem();
			Block b=item.worldObj.getBlockState(pos).getBlock();
			EnumFacing up=EnumFacing.UP;
			if(i==Items.nether_wart&&b.canSustainPlant(w, pos, up, (IPlantable)Items.nether_wart))
				w.setBlockState(pos, Blocks.nether_wart);
			else if(i==Items.potato&&b.canSustainPlant(w, x, y, z, up, (IPlantable)Items.potato))
				w.setBlock(x, y+1, z, Blocks.potatoes);
			else if(i==Items.carrot&&b.canSustainPlant(w, x, y, z, up, (IPlantable)Items.carrot))
				w.setBlock(x, y+1, z, Blocks.carrots);
			else if(i==Items.wheat_seeds&&b.canSustainPlant(w, x, y, z, up, (IPlantable)Items.wheat_seeds))
				w.setBlock(x, y+1, z, Blocks.wheat);
			else if(i==Items.melon_seeds&&b.canSustainPlant(w, x, y, z, up, (IPlantable)Items.melon_seeds))
				w.setBlock(x, y+1, z, Blocks.melon_stem);
			else if(i==Items.pumpkin_seeds&&b.canSustainPlant(w, x, y, z, up, (IPlantable)Items.pumpkin_seeds))
				w.setBlock(x, y+1, z, Blocks.pumpkin_stem);
			else if(i==ItemBlock.getItemFromBlock(Blocks.sapling)&&b.canSustainPlant(w, x, y, z, up, (IPlantable)Blocks.sapling))
				w.setBlock(x, y+1, z, Blocks.sapling, item.getEntityItem().getItemDamage(), 2);
			else
				return;
			item.getEntityItem().stackSize--;
			if(item.getEntityItem().stackSize<1)
				item.setDead();
		}
	}

}
