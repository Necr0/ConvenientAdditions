package convenientadditions.api.capabilities.stackhandler;

import convenientadditions.api.capabilities.IAutoSavable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerAutoSave extends ItemStackHandler implements IItemHandlerModifiable, IAutoSavable {
    public TileEntity te;
    public boolean causeUpdate=false;
    public int updateFlag=3;
    public String name="INV";

    public ItemStackHandlerAutoSave(TileEntity tile, int slots) {
        super(slots);
        this.te = tile;
    }


    public ItemStackHandlerAutoSave(TileEntity tile) {
        super(1);
        this.te = tile;
    }

    @Override
    protected void onContentsChanged(int slot) {
        te.markDirty();
        if(causeUpdate){
            IBlockState state = te.getWorld().getBlockState(te.getPos());
            te.getWorld().notifyBlockUpdate(te.getPos(), state, state, updateFlag);
        }
    }

    public NonNullList<ItemStack> getStacks(){
        return this.stacks;
    }

    public void setStacks(NonNullList<ItemStack> stacks){
        this.stacks=stacks;
        for (int i = 0; i < getSlots(); i++)
            onContentsChanged(i);
    }

    public ItemStackHandlerAutoSave setCauseUpdate(boolean causeUpdate) {
        this.causeUpdate = causeUpdate;
        return this;
    }

    public ItemStackHandlerAutoSave setUpdateFlag(int updateFlag) {
        this.updateFlag = updateFlag;
        return this;
    }

    public ItemStackHandlerAutoSave setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public void loadFromTagCompound(NBTTagCompound nbt) {
        if(nbt.hasKey(name, Constants.NBT.TAG_COMPOUND))
            deserializeNBT(nbt.getCompoundTag(name));
    }

    @Override
    public void saveToTagCompound(NBTTagCompound nbt) {
        nbt.setTag(name,serializeNBT());
    }
}
