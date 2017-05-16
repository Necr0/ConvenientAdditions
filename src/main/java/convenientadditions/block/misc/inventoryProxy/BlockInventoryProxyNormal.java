package convenientadditions.block.misc.inventoryProxy;

import convenientadditions.ModConstants;

public class BlockInventoryProxyNormal extends BlockInventoryProxy{
    public BlockInventoryProxyNormal() {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.inventoryProxy).setRegistryName(ModConstants.BlockNames.inventoryProxy);
    }
}
