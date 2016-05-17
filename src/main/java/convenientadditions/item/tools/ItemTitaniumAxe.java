package convenientadditions.item.tools;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import net.minecraft.item.ItemAxe;

public class ItemTitaniumAxe extends ItemAxe implements IModelResourceLocationProvider {
	public ItemTitaniumAxe(ToolMaterial p_i45327_1_) {
		super(p_i45327_1_);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
}
