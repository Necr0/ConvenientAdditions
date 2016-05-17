package convenientadditions.block.charge;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.charge.TileEntityChargeAccumulator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockChargeAccumulator extends BlockMachineConfigurable {

	public BlockChargeAccumulator() {
		super(Material.rock);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.chargeAccumulatorBlockName).setHardness(2F).setResistance(3F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityChargeAccumulator();
	}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
