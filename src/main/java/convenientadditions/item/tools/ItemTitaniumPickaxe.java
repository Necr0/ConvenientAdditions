package convenientadditions.item.tools;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.item.ItemPickaxe;

public class ItemTitaniumPickaxe extends ItemPickaxe implements IModelResourceLocationProvider {
	public ItemTitaniumPickaxe(ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.pickaxeTitaniumItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}
}
