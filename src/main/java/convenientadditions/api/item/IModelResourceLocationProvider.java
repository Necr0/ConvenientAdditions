package convenientadditions.api.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelResourceLocationProvider {
	@SideOnly(Side.CLIENT)
	public default ModelResourceLocation getModelResourceLocation(){
		return new ModelResourceLocation(((Item)this).getUnlocalizedName().substring(5),"inventory");
	}
}
