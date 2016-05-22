package convenientadditions.block;

import conveniencecore.block.BlockConfigurable;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.seedbox.TileEntitySeedBox;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockSeedBox extends BlockConfigurable {

	public BlockSeedBox() {
		super(Material.wood);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.seedBoxBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.setStepSound(SoundType.WOOD);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySeedBox();
	}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
