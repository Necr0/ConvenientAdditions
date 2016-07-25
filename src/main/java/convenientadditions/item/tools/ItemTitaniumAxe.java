package convenientadditions.item.tools;

import conveniencecore.item.ItemToolAxeBase;
import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;

public class ItemTitaniumAxe extends ItemToolAxeBase implements IModelResourceLocationProvider {
    public ItemTitaniumAxe(ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
        this.damageVsEntity = 8F;
        this.attackSpeed = -3.05F;
	}
}
