package convenientadditions.item.tools;

import conveniencecore.item.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.ItemToolAxeBase;

public class ItemTitaniumAxe extends ItemToolAxeBase implements IModelResourceLocationProvider {
    public ItemTitaniumAxe(ToolMaterial material) {
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
        this.damageVsEntity = 8F;
        this.attackSpeed = -3.05F;
	}
}
