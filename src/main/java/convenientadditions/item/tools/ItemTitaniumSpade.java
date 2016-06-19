package convenientadditions.item.tools;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.item.ItemSpade;

public class ItemTitaniumSpade extends ItemSpade implements IModelResourceLocationProvider {
	public ItemTitaniumSpade(ToolMaterial p_i45353_1_) {
		super(p_i45353_1_);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.spadeTitaniumItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}
}
