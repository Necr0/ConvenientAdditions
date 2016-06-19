package convenientadditions.item.tools;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.item.ItemSword;

public class ItemTitaniumSword extends ItemSword implements IModelResourceLocationProvider {
	public ItemTitaniumSword(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.swordTitaniumItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}
}
