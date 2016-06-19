package convenientadditions.block;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.block.BlockOre;

public class BlockTitaniumOre extends BlockOre implements IModelResourceLocationProvider {
	public BlockTitaniumOre() {
		super();
		this.setHardness(3.0F).setResistance(5.0F).setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.oreTitaniumBlockName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
		this.setHarvestLevel("pickaxe", 2);
    }
}
