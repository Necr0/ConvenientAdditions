package convenientadditions.item.misc.backpack;

import convenientadditions.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Necro on 5/18/2017.
 */
public class CapabilityProviderBackpack implements ICapabilityProvider {

    ItemStack backpack;
    ItemHandlerBackpack handler;
    boolean initialized=false;

    public CapabilityProviderBackpack(ItemStack backpack){
        this.backpack=backpack;
        this.handler=new ItemHandlerBackpack(backpack,9);
    }

    public void initHandler(){
        if(!initialized){
            handler.read();
            initialized=true;
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        initHandler();
        return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY&&backpack.getItem()==ModItems.itemBackpack;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        initHandler();
        return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY&&backpack.getItem()==ModItems.itemBackpack?(T)this.handler:null;
    }
}
