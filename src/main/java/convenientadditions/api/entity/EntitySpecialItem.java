package convenientadditions.api.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySpecialItem extends EntityItem {

	public EntitySpecialItem(World p_i1711_1_) {
		super(p_i1711_1_);
	}

	public EntitySpecialItem(World p_i1709_1_, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_) {
		super(p_i1709_1_, p_i1709_2_, p_i1709_4_, p_i1709_6_);
	}

	public EntitySpecialItem(World p_i1710_1_, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_) {
		super(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_, p_i1710_8_);
	}

}
