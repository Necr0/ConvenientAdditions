package convenientadditions.api.entity.behaviour;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
			BlockPos pos_e=new BlockPos(x,y+1,z);
			if(i==Items.nether_wart&&b.canSustainPlant(w, pos, up, (IPlantable)Items.nether_wart))
				w.setBlockState(pos_e, Blocks.nether_wart.getStateFromMeta(0), 2);
			else if(i==Items.potato&&b.canSustainPlant(w, pos, up, (IPlantable)Items.potato))
				w.setBlockState(pos_e, Blocks.potatoes.getStateFromMeta(0), 2);
			else if(i==Items.carrot&&b.canSustainPlant(w, pos, up, (IPlantable)Items.carrot))
				w.setBlockState(pos_e, Blocks.carrots.getStateFromMeta(0), 2);
			else if(i==Items.wheat_seeds&&b.canSustainPlant(w, pos, up, (IPlantable)Items.wheat_seeds))
				w.setBlockState(pos_e, Blocks.wheat.getStateFromMeta(0), 2);
			else if(i==Items.melon_seeds&&b.canSustainPlant(w, pos, up, (IPlantable)Items.melon_seeds))
				w.setBlockState(pos_e, Blocks.melon_stem.getStateFromMeta(0), 2);
			else if(i==Items.pumpkin_seeds&&b.canSustainPlant(w, pos, up, (IPlantable)Items.pumpkin_seeds))
				w.setBlockState(pos_e, Blocks.pumpkin_stem.getStateFromMeta(0), 2);
			else if(i==ItemBlock.getItemFromBlock(Blocks.sapling)&&b.canSustainPlant(w, pos, up, (IPlantable)Blocks.sapling))
				w.setBlockState(pos_e, Blocks.sapling.getStateFromMeta(item.getEntityItem().getItemDamage()), 2);
			else
				return;
			item.getEntityItem().stackSize--;
			if(item.getEntityItem().stackSize<1)
				item.setDead();
		}
	}

}
