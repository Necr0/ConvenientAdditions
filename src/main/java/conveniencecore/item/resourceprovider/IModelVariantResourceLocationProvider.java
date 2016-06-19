package conveniencecore.item.resourceprovider;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelVariantResourceLocationProvider extends IResourceLocationProvider {
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation[] getModelResourceLocations();
}
