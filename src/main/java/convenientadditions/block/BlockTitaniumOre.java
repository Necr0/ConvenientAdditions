package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.block.BlockOre;

public class BlockTitaniumOre extends BlockOre {
	public BlockTitaniumOre() {
		super();
		this.setHardness(3.0F).setResistance(5.0F).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.oreTitaniumBlockName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.setHarvestLevel("pickaxe", 2);
    }
}
