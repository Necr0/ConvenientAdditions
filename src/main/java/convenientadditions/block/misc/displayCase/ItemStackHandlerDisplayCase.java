package convenientadditions.block.misc.displayCase;

import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

/**
 * Created by Necro on 3/10/2017.
 */
public class ItemStackHandlerDisplayCase extends ItemStackHandlerAutoSave {
    public ItemStackHandlerDisplayCase(TileEntity tile) {
        super(tile);
    }

    @Override
    public int getStackLimit(int slot, @Nonnull ItemStack stack)
    {
        return 1;
    }
}
