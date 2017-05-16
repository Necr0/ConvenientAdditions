package convenientadditions.block.misc.inventoryProxy;

import convenientadditions.ModConstants;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockInventoryProxySided extends BlockInventoryProxy {
    public BlockInventoryProxySided() {
        super();
        this.setRegistryName(ModConstants.BlockNames.inventoryProxySided).setUnlocalizedName(ModConstants.Mod.MODID + ":" + ModConstants.BlockNames.inventoryProxySided);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityInventoryProxy(true);
    }

}
