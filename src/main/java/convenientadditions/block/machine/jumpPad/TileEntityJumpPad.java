package convenientadditions.block.machine.jumpPad;

import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.base.block.CATileEntity;
import convenientadditions.item.module.ItemLocationModule;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class TileEntityJumpPad extends CATileEntity {
    public ItemStackHandlerAutoSave location = addAutoSavable(new ItemStackHandlerAutoSaveRestricted(this, 2, ItemLocationModule.class).setName("LOCATION"));

    @Nullable
    public BlockPos getCustomLocation(boolean jump){
        ItemStack module=location.getStackInSlot(jump?0:1);
        if(!module.isEmpty()){
            ItemLocationModule item=((ItemLocationModule)module.getItem());
            if(item.hasLocation(module)&&item.getDimension(module)==world.provider.getDimension())
                return item.getLocation(module);
        }
        return null;
    }
}
