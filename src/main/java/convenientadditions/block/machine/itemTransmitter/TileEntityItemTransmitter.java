package convenientadditions.block.machine.itemTransmitter;

import convenientadditions.api.IMatcher;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.api.item.IMatcherProvider;
import convenientadditions.api.provider.itemnetwork.IItemProvider;
import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.base.block.tileentity.CATileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;

public class TileEntityItemTransmitter extends CATileEntity implements ITickable, IItemProvider {

    ItemStackHandlerAutoSave channels = addAutoSavable(new ItemStackHandlerAutoSaveRestricted(this, 3, IMatcherProvider.class).setName("CHANNELS"));
    ItemStackHandlerAutoSave buffer = addCapability(addAutoSavable(new ItemStackHandlerAutoSave(this, 9).setName("BUFFER")));

    @Override
    public IItemHandler getItemHandler(){ return this.buffer; }

    @Override
    public boolean hasItemHandler(){ return true; }

    @Override
    public IMatcher[] getAccess() {
        ArrayList<IMatcher> a = new ArrayList<>();
        for (ItemStack s : channels.getStacks()) {
            if (!s.isEmpty() && ((IMatcherProvider) s.getItem()).hasMatcher(s))
                a.add(((IMatcherProvider) s.getItem()).getMatcher(s));
        }
        return a.toArray(new IMatcher[a.size()]);
    }

    @Override
    public void update() {
        ItemNetworkProvider.addEntry(getWorld(), getPos());
    }
}
