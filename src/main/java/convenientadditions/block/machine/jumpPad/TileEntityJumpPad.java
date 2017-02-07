package convenientadditions.block.machine.jumpPad;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.base.CATileEntity;
import convenientadditions.item.ItemLocationModule;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class TileEntityJumpPad extends CATileEntity {
    public ItemStackHandlerAutoSaveRestricted location;

    public TileEntityJumpPad() {
        this.location = new ItemStackHandlerAutoSaveRestricted(this, 1,ItemLocationModule.class);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("LOCATION") && nbt.getTag("LOCATION") instanceof NBTTagCompound)
            location.deserializeNBT((NBTTagCompound) nbt.getTag("LOCATION"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("LOCATION", location.serializeNBT());
        return nbt;
    }

    @Nullable
    public BlockPos getCustomLocation(){
        ItemStack module=location.getStackInSlot(0);
        if(!module.isEmpty()){
            ItemLocationModule item=((ItemLocationModule)module.getItem());
            if(item.hasLocation(module)&&item.getDimension(module)==world.provider.getDimension())
                return item.getLocation(module);
        }
        return null;
    }
}
