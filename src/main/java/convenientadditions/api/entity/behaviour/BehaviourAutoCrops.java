package convenientadditions.api.entity.behaviour;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;

public class BehaviourAutoCrops implements IEntitySpecialItemBehaviour {

	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		return true;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		if(item.onGround){
			Item i=item.getEntityItem().getItem();
			World w=item.worldObj;
			int x=MathHelper.floor_double(item.posX),y=MathHelper.floor_double(item.posY)-1,z=MathHelper.floor_double(item.posZ);
			Block b=item.worldObj.getBlock(x,y,z);
			ForgeDirection up=ForgeDirection.UP;
			if(i==Items.nether_wart&&b.canSustainPlant(w, x, y, z, up, (IPlantable)Items.nether_wart))
				w.setBlock(x, y+1, z, Blocks.nether_wart);
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
