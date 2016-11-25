package convenientadditions.block.inventoryProxy;

import convenientadditions.ModConstants;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInventoryProxySided extends BlockInventoryProxy {
    public BlockInventoryProxySided() {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.inventoryProxySidedBlockName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityInventoryProxy(true);
    }

}
