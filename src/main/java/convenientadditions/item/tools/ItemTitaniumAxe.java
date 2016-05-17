package convenientadditions.item.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import convenientadditions.api.item.ItemToolBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ItemTitaniumAxe extends ItemToolBase implements IModelResourceLocationProvider {
    public ItemTitaniumAxe(ToolMaterial material) {
		super(material,ItemToolBase.EnumToolType.axe);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
        this.damageVsEntity = 8F;
        this.attackSpeed = -3.05F;
	}
}
