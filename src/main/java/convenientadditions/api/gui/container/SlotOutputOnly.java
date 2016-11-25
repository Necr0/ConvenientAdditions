package convenientadditions.api.gui.container;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotOutputOnly extends SlotItemHandler {

	public SlotOutputOnly(IItemHandler handler, int index, int xPosition, int yPosition) {
		super(handler, index, xPosition, yPosition);
	}
	
	@Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }
}
