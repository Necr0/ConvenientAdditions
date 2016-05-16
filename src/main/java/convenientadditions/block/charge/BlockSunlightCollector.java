package convenientadditions.block.charge;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.charge.TileEntitySunlightCollector;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSunlightCollector extends BlockMachine {

	public BlockSunlightCollector() {
		super(Material.rock);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySunlightCollector();
	}
}
