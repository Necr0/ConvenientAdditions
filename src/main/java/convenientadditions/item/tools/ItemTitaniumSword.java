package convenientadditions.item.tools;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import net.minecraft.item.ItemSword;

public class ItemTitaniumSword extends ItemSword implements IModelResourceLocationProvider {
	public ItemTitaniumSword(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.swordTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
}
