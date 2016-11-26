package convenientadditions.api.block.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerAutoSave extends ItemStackHandler implements IItemHandlerModifiable {
    public TileEntity te;
    public boolean causeUpdate=false;
    public int updateFlag=3;

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
}
