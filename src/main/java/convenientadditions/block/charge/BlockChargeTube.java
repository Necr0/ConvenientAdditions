package convenientadditions.block.charge;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityChargeTube;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChargeTube extends BlockMachine {

	public BlockChargeTube() {
		super(Material.rock);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.chargeTubeBlockName).setHardness(1F).setResistance(1F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityChargeTube();
	}

}
