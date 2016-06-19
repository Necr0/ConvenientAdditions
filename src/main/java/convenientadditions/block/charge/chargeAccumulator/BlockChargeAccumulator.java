package convenientadditions.block.charge.chargeAccumulator;

import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.block.BlockMachineConfigurable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockChargeAccumulator extends BlockMachineConfigurable {

	public BlockChargeAccumulator() {
		super(Material.rock);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.chargeAccumulatorBlockName).setHardness(2F).setResistance(3F);
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
