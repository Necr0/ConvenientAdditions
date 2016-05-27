package convenientadditions.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerTest implements ICapabilityProvider {

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return (capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)?
				(T)handler:
				null;
	}

	IItemHandler handler=new ItemStackHandler(15);
}
