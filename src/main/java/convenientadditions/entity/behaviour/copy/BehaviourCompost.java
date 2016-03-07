package convenientadditions.entity.behaviour.copy;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.api.entity.behaviour.IEntitySpecialItemBehaviour;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;

public class BehaviourCompost implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {return true;}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		World w=item.worldObj;
		int x=MathHelper.floor_double(item.posX),y=MathHelper.floor_double(item.posY)-1,z=MathHelper.floor_double(item.posZ);
		if(item.onGround){
			Item i=item.getEntityItem().getItem();
			Block b=item.worldObj.getBlock(x,y,z);
			ForgeDirection up=ForgeDirection.UP;
			if(i!=ModItems.itemCompost)
				return;
			if(b==Blocks.dirt||b==Blocks.grass)
				w.setBlock(x, y, z, ModBlocks.compostSoilBlock, 0, 2);
			else if(b==Blocks.farmland)
				w.setBlock(x, y, z, ModBlocks.compostSoilTilledBlock, 0, 2);
			else if((b==ModBlocks.compostSoilBlock||b==ModBlocks.compostSoilTilledBlock)&&w.getBlockMetadata(x, y, z)!=0)
				w.setBlockMetadataWithNotify(x, y, z, 0, 2+4);
			else
				return;
			item.getEntityItem().stackSize--;
			if(item.getEntityItem().stackSize<1)
				item.setDead();
		}
	}

}
