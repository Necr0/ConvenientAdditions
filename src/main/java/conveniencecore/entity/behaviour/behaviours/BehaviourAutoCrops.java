package conveniencecore.entity.behaviour.behaviours;

import conveniencecore.entity.behaviour.IEntitySpecialItemBehaviour;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
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
		BlockPos pos_e=new BlockPos(x,y+1,z);
		IBlockState state=w.getBlockState(pos);
		IBlockState state_e=w.getBlockState(pos_e);
		if(item.onGround&&state_e.getBlock().isAir(state_e,w, pos_e)){
			Item i=item.getEntityItem().getItem();
			Block b=state.getBlock();
			EnumFacing up=EnumFacing.UP;
			if(i instanceof IPlantable){
				IPlantable plantable=(IPlantable)i;
				if(b.canSustainPlant(state, w, pos, up, plantable)){
					w.setBlockState(pos_e, plantable.getPlant(w, pos_e));
					item.getEntityItem().stackSize--;
					if(item.getEntityItem().stackSize<1)
						item.setDead();
				}
			}else if(i instanceof ItemBlock&&((ItemBlock)i).getBlock() instanceof IPlantable){
				IPlantable plantable=(IPlantable)((ItemBlock)i).getBlock() ;
				if(b.canSustainPlant(state, w, pos, up, plantable)){
					w.setBlockState(pos_e, plantable.getPlant(w, pos_e).getBlock().getStateFromMeta(((ItemBlock)i).getMetadata(item.getEntityItem())));
					item.getEntityItem().stackSize--;
					if(item.getEntityItem().stackSize<1)
						item.setDead();
				}
			}else
				return;
		}
	}

}
