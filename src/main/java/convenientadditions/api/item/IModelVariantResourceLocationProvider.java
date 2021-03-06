package convenientadditions.api.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelVariantResourceLocationProvider {
    @SideOnly(Side.CLIENT)
    ModelResourceLocation[] getModelResourceLocations();
}
