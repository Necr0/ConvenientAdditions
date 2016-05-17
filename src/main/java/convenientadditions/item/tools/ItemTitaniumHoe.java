package convenientadditions.item.tools;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import net.minecraft.item.ItemHoe;

public class ItemTitaniumHoe extends ItemHoe implements IModelResourceLocationProvider {
	public ItemTitaniumHoe(ToolMaterial p_i45343_1_) {
		super(p_i45343_1_);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.hoeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
}
