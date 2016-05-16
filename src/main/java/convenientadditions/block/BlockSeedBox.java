package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.block.BlockConfigurable;
import convenientadditions.tileentity.seedbox.TileEntitySeedBox;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSeedBox extends BlockConfigurable {

	public BlockSeedBox() {
		super(Material.wood);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.seedBoxBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySeedBox();
	}
}
