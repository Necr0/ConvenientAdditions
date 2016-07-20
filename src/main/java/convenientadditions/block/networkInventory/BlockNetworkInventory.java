package convenientadditions.block.networkInventory;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockNetworkInventory extends BlockContainer implements IModelResourceLocationProvider {
	
	public BlockNetworkInventory() {
		super(Material.ROCK);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.networkInventoryBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityNetworkInventory();
	}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
