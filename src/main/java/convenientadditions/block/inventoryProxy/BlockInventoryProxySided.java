package convenientadditions.block.inventoryProxy;

import convenientadditions.ModConstants;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInventoryProxySided extends BlockInventoryProxy {
    public BlockInventoryProxySided() {
        super();
        this.setRegistryName(ModConstants.BlockNames.inventoryProxySided).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.inventoryProxySided);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityInventoryProxy(true);
    }

}
