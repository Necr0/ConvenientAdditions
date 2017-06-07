package convenientadditions.block.misc.workStation;

import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.base.block.tileentity.CATileEntity;

/**
 * Created by Necro on 5/7/2017.
 */
public class TileEntityWorkStation extends CATileEntity {
    public ItemStackHandlerAutoSave inv=addCapability(addAutoSavable(new ItemStackHandlerAutoSave(this,9)));
    public ItemStackHandlerAutoSave grid=addAutoSavable(new ItemStackHandlerAutoSave(this,9).setName("GRID"));
}
