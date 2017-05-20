package convenientadditions.item.misc.backpack;

import convenientadditions.config.ModConfigMisc;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Created by Necro on 5/18/2017.
 */
public class ItemHandlerBackpack extends ItemStackHandler {
    ItemStack backpack;

    public ItemHandlerBackpack(ItemStack backpack, int slots){
        super(slots);
        this.backpack=backpack;
    }

    public NBTTagCompound initNBT(){
        if(!backpack.hasTagCompound())
            backpack.setTagCompound(new NBTTagCompound());
        return backpack.getTagCompound();
    }

    public ItemHandlerBackpack read(){
        NBTTagCompound nbt=initNBT();
        if(nbt.hasKey("BACKPACK_CONTENTS"))
            this.deserializeNBT(nbt.getCompoundTag("BACKPACK_CONTENTS"));
        return this;
    }

    @Override
    public void onContentsChanged(int slot)
    {
        NBTTagCompound nbt=initNBT();
        nbt.setTag("BACKPACK_CONTENTS",this.serializeNBT());
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if(stack.getItem() instanceof ItemBackpack||Arrays.asList(ModConfigMisc.backpack_blacklist).contains(stack.getItem().getRegistryName()))
            return stack;
        return super.insertItem(slot,stack,simulate);
    }
}
