package convenientadditions.block.misc.displayCase;

import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.base.block.CATileEntity;

/**
 * Created by Necro on 3/10/2017.
 */
public class TileEntityDisplayCase extends CATileEntity {
    public ItemStackHandlerAutoSave inventory=addCapability(addAutoSavable(new ItemStackHandlerDisplayCase(this).setCauseUpdate(true).setUpdateFlag(0).setName("INVENTORY")));
}
