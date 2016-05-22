package convenientadditions.item.tools;

import conveniencecore.item.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraft.item.ItemHoe;

public class ItemTitaniumHoe extends ItemHoe implements IModelResourceLocationProvider {
	public ItemTitaniumHoe(ToolMaterial p_i45343_1_) {
		super(p_i45343_1_);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.hoeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
}
