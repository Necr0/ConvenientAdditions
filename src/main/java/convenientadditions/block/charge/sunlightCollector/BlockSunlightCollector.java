package convenientadditions.block.charge.sunlightCollector;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.block.BlockMachine;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSunlightCollector extends BlockMachine {

	public BlockSunlightCollector() {
		super(Material.rock);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.sunlightCollectorBlockName).setHardness(2F).setResistance(3F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySunlightCollector();
	}
}
