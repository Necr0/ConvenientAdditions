package convenientadditions.item.trinket.doubleJump;

import convenientadditions.api.inventory.SlotNotation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by Necro on 2/28/2017.
 */
public interface IDoubleJumpProvider {
    boolean canDoubleJump(EntityPlayer player, ItemStack stack, SlotNotation slot);
    void onDoubleJump(EntityPlayer player, ItemStack stack, SlotNotation slot);
    boolean isParachute(EntityPlayer player, ItemStack stack, SlotNotation slot);
    void onParachuteOpen(EntityPlayer player, ItemStack stack, SlotNotation slot);
}
