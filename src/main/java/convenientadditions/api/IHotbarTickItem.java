package convenientadditions.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IHotbarTickItem {
	public void onHotbarTick(World w,EntityPlayer player,ItemStack item,int slot);
}
