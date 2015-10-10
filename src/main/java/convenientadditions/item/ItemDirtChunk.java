package convenientadditions.item;

import net.minecraft.item.Item;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Reference;

public class ItemDirtChunk extends Item {
	public ItemDirtChunk(){
		super();
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
}
