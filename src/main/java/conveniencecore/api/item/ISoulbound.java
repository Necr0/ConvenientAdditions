package conveniencecore.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISoulbound {
	public boolean isSoulbound(ItemStack i,EntityPlayer p);
}
