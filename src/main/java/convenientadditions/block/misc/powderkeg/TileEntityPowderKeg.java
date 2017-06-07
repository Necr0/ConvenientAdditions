package convenientadditions.block.misc.powderkeg;

import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.base.block.tileentity.CATileEntity;
import net.minecraft.init.Items;

public class TileEntityPowderKeg extends CATileEntity {

    public ItemStackHandlerAutoSave inventory = addCapability(addAutoSavable(new ItemStackHandlerAutoSaveRestricted(this, Items.GUNPOWDER).setCauseUpdate(true).setUpdateFlag(6).setName("INVENTORY")));
}
