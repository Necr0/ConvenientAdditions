package convenientadditions.api.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public interface IFakeSlot {
    ItemStack slotClick(Container container, int button, ClickType mode, EntityPlayer player);
}
