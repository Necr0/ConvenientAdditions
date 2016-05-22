package conveniencecore.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IResourceLocationProvider {
	@SideOnly(Side.CLIENT)
	public default String getResourceLocation(){
		return ((Item)this).getUnlocalizedName().substring(5);
	}
}
