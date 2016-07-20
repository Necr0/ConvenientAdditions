package convenientadditions.block.inventoryProxy;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInventoryProxySided extends BlockInventoryProxy {
	public BlockInventoryProxySided() {
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.inventoryProxySidedBlockName);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityInventoryProxy(true);
	}

}
