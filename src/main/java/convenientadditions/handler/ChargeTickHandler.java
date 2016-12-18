package convenientadditions.handler;

import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.InventoryIterator;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.charge.ISunlightChargeable;
import convenientadditions.api.util.Helper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ChargeTickHandler {
    private byte time = 0;

    @SubscribeEvent
    public void onPlayerSunlightChargeTick(TickEvent.PlayerTickEvent e) {
        time++;
        if (time < 20 || e.side != Side.SERVER)
            return;
        time = 0;

        EntityPlayer player = e.player;

        if (player.getEntityWorld().isDaytime() && !player.getEntityWorld().isRaining() && Helper.canEntitySeeSky(player)) {
            Iterable<SlotNotation> iter=InventoryIterator.getIterable(player, EnumInventory.MAIN, EnumInventory.BAUBLES);
            for (SlotNotation slot : iter) {
                ItemStack stack=slot.getItem();
                if (stack != null && stack.getItem() instanceof ISunlightChargeable) {
                    ISunlightChargeable sitem = (ISunlightChargeable) (stack.getItem());
                    if (sitem.isSunlightChargeable(stack, slot, player)) {
                        sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, slot, player) * 20);
                    }
                }
            }
        }
    }

}
