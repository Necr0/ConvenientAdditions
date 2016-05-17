package convenientadditions.item.tools;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import net.minecraft.item.ItemPickaxe;

public class ItemTitaniumPickaxe extends ItemPickaxe implements IModelResourceLocationProvider {
	public ItemTitaniumPickaxe(ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.pickaxeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
}
