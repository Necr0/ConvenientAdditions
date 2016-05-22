package convenientadditions.entity.behaviour;

import conveniencecore.behaviours.IEntitySpecialItemBehaviour;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
		IBlockState state=item.worldObj.getBlockState(new BlockPos(pos));
		Block b=state.getBlock();
		if(item.onGround){
			if(!(b==Blocks.dirt||b==Blocks.farmland||b==Blocks.grass||((b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)&&ModBlocks.compostSoilTilledBlock.getMetaFromState(state)!=0)))
	    		return;
			Item i=item.getEntityItem().getItem();
			EnumFacing up=EnumFacing.UP;
			if(i!=ModItems.itemCompost)
				return;
			if(b==Blocks.dirt||b==Blocks.grass)
				w.setBlockState(pos, ModBlocks.compostSoilBlock.getStateFromMeta(0), 2);
			else if(b==Blocks.farmland)
				w.setBlockState(pos, ModBlocks.compostSoilTilledBlock.getStateFromMeta(0), 2);
			else if(b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)
				w.setBlockState(pos, b.getStateFromMeta(0), 2+4);
			else
				return;
			item.getEntityItem().stackSize--;
			if(item.getEntityItem().stackSize<1)
				item.setDead();
		}
	}

}
