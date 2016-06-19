package convenientadditions.item.tools;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.item.ItemHoe;

public class ItemTitaniumHoe extends ItemHoe implements IModelResourceLocationProvider {
	public ItemTitaniumHoe(ToolMaterial p_i45343_1_) {
		super(p_i45343_1_);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.hoeTitaniumItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}
}
