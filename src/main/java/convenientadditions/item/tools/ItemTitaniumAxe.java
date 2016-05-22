package convenientadditions.item.tools;

import conveniencecore.item.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.ItemToolBase;

public class ItemTitaniumAxe extends ItemToolBase implements IModelResourceLocationProvider {
    public ItemTitaniumAxe(ToolMaterial material) {
		super(material,ItemToolBase.EnumToolType.axe);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
        this.damageVsEntity = 8F;
        this.attackSpeed = -3.05F;
	}
}
