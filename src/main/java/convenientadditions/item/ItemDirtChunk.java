package convenientadditions.item;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Reference;
import net.minecraft.item.Item;

public class ItemDirtChunk extends Item {
	public ItemDirtChunk(){
		super();
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
}
