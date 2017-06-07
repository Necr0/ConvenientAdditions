package convenientadditions.block.machine.itemReceiver;

import convenientadditions.api.IMatcher;
import convenientadditions.api.capabilities.stackhandler.CombinedItemStackHandler;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.api.item.IMatcherProvider;
import convenientadditions.api.provider.itemnetwork.IItemProvider;
import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;

public class TileEntityItemReceiver extends CATileEntity {

    ItemStackHandlerAutoSave channels = addAutoSavable(new ItemStackHandlerAutoSaveRestricted(this, 3, IMatcherProvider.class).setName("CHANNELS"));

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) getItemHandler() : super.getCapability(capability, facing);
    }

    public IItemHandler getItemHandler() {
        ArrayList<IItemHandler> tmp = new ArrayList<>();
        for (IMatcher m : getMatchers()) {
            for (Tuple<Integer, BlockPos> t : ItemNetworkProvider.getEntries(m)) {
                IItemProvider provider=ItemNetworkProvider.getProvider(t.getFirst(), t.getSecond());
                if (provider!=null && provider.hasItemHandler()){
                    IItemHandler a=provider.getItemHandler();
                    if (!tmp.contains(a))
                        tmp.add(a);
                }
            }
        }
        return new CombinedItemStackHandler(tmp);
    }

    public IMatcher[] getMatchers() {
        ArrayList<IMatcher> a = new ArrayList<>();
        for (ItemStack s : channels.getStacks()) {
            if (!s.isEmpty() && ((IMatcherProvider) s.getItem()).hasMatcher(s))
                a.add(((IMatcherProvider) s.getItem()).getMatcher(s));
        }
        return a.toArray(new IMatcher[a.size()]);
    }
}
