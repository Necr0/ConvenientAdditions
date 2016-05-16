package convenientadditions.block.charge;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.charge.TileEntityChargeAccumulator;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChargeAccumulator extends BlockMachineConfigurable {

	public BlockChargeAccumulator() {
		super(Material.rock);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.chargeAccumulatorBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityChargeAccumulator();
	}
}
