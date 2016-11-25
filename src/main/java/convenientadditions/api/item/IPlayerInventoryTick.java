package convenientadditions.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IPlayerInventoryTick {
    public void onPlayerInventoryTick(ItemStack item, int slot, EntityPlayer player);
}
