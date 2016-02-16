package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntitySunlightCollector;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSunlightCollector extends BlockContainer {

	public BlockSunlightCollector() {
		super(Material.rock);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
		.setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySunlightCollector();
	}

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }
	
}
