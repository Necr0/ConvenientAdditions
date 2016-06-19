package conveniencecore.item.resourceprovider;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IResourceLocationProvider {
	@SideOnly(Side.CLIENT)
	public default String getResourceLocation(){
		return (this instanceof Item)?((Item)this).getUnlocalizedName().substring(5):
			   (this instanceof Block)?((Block)this).getUnlocalizedName().substring(5):
				   null;
	}
}
