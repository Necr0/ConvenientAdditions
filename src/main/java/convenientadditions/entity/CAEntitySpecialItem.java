package convenientadditions.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import convenientadditions.api.entity.EntitySpecialItem;

public class CAEntitySpecialItem extends EntitySpecialItem {
	public CAEntitySpecialItem(World p_i1711_1_) {
		super(p_i1711_1_);
	}

	public CAEntitySpecialItem(World p_i1709_1_, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_) {
		super(p_i1709_1_, p_i1709_2_, p_i1709_4_, p_i1709_6_);
	}

	public CAEntitySpecialItem(World p_i1710_1_, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_) {
		super(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_, p_i1710_8_);
	}
}
