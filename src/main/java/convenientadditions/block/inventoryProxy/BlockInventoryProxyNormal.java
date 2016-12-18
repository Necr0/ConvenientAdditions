package convenientadditions.block.inventoryProxy;

import convenientadditions.ModConstants;

public class BlockInventoryProxyNormal extends BlockInventoryProxy{
    public BlockInventoryProxyNormal() {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.inventoryProxyBlockName).setRegistryName(ModConstants.BlockNames.inventoryProxyBlockName);
    }
}
