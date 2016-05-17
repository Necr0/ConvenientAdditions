package convenientadditions.api.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public interface IModelResourceLocationProvider {
	public default ModelResourceLocation getModelResourceLocation(){
		return new ModelResourceLocation(((Item)this).getUnlocalizedName());
	}
}
