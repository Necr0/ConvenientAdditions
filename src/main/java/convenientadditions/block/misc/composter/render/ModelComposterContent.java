package convenientadditions.block.misc.composter.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelComposterContent - Undefined
 * Created using Tabula 4.1.1
 */
public class ModelComposterContent extends ModelBase {
    public ModelRenderer shape1;

    public ModelComposterContent() {
        this.textureWidth = 64;
        this.textureHeight = 33;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-8.0F, 8.0F, -8.0F);
        this.shape1.addBox(1.0F, 14.0F, 1.0F, 14, 1, 14, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
