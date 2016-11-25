package convenientadditions.entity.launchingArrow;

import convenientadditions.ModConstants;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLaunchingArrow extends RenderArrow<EntityLaunchingArrow> {
    public static final ResourceLocation RES_ARROW_0 = new ResourceLocation(ModConstants.Mod.MODID + ":textures/entity/" + ModConstants.Entities.launchingArrowEntityName + "/" + ModConstants.Entities.launchingArrowEntityName + "_0.png");
    public static final ResourceLocation RES_ARROW_1 = new ResourceLocation(ModConstants.Mod.MODID + ":textures/entity/" + ModConstants.Entities.launchingArrowEntityName + "/" + ModConstants.Entities.launchingArrowEntityName + "_1.png");
    public static final ResourceLocation RES_ARROW_2 = new ResourceLocation(ModConstants.Mod.MODID + ":textures/entity/" + ModConstants.Entities.launchingArrowEntityName + "/" + ModConstants.Entities.launchingArrowEntityName + "_2.png");

    public RenderLaunchingArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLaunchingArrow entity) {
        switch (entity.getVariant()) {
            case creeper:
                return RES_ARROW_0;
            case blast:
                return RES_ARROW_1;
            case slime:
                return RES_ARROW_2;
            default:
                return RES_ARROW_2;
        }
    }

}
