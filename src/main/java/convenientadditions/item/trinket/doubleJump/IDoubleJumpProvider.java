package convenientadditions.item.trinket.doubleJump;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by Necro on 2/28/2017.
 */
public interface IDoubleJumpProvider {
    boolean canDoubleJump(EntityPlayer player, ItemStack stack);
    void onDoubleJump(EntityPlayer player, ItemStack stack);
}
