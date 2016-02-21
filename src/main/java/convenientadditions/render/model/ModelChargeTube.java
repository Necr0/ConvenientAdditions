package convenientadditions.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelChargeTube - Necr0
 * Created using Tabula 4.1.1
 */
public class ModelChargeTube extends ModelBase {
    public ModelRenderer curvedOutput;

    public ModelChargeTube() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.curvedOutput = new ModelRenderer(this, 0, 0);
        this.curvedOutput.setRotationPoint(-4.0F, 16.0F, 0.0F);
        this.curvedOutput.addBox(2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.curvedOutput.render(f5);
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
