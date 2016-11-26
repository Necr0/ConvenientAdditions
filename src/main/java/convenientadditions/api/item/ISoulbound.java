package convenientadditions.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISoulbound {
    boolean isSoulbound(ItemStack i, EntityPlayer p);
}
