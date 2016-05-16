package convenientadditions.entity.behaviour;

import java.util.Random;

import convenientadditions.api.entity.behaviour.IEntitySpecialItemBehaviour;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BehaviourCompost implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {return true;}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		World w=item.worldObj;
		BlockPos pos=new BlockPos(MathHelper.floor_double(item.posX),MathHelper.floor_double(item.posY)-1,MathHelper.floor_double(item.posZ));
		Block b=item.worldObj.getBlockState(new BlockPos(pos)).getBlock();
		if(item.onGround){
			if(!(b==Blocks.dirt||b==Blocks.farmland||b==Blocks.grass||((b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)&&METADATA!=0)))
	    		return false;
			Item i=item.getEntityItem().getItem();
			EnumFacing up=EnumFacing.UP;
			if(i!=ModItems.itemCompost)
				return;
			if(b==Blocks.dirt||b==Blocks.grass)
				w.setBlockState(pos, ModBlocks.compostSoilBlock.getStateFromMeta(0), 2);
			else if(b==Blocks.farmland)
				w.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getStateFromMeta(0), 2);
			else if((b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)&&w.getBlockMetadata(x, y, z)!=0)
				w.setBlockState(pos, b.getStateFromMeta(0), 2+4);
			else
				return;
			item.getEntityItem().stackSize--;
			if(item.getEntityItem().stackSize<1)
				item.setDead();
		}
	}

}
