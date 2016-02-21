package convenientadditions.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelChargeTubeConnector - Necr0
 * Created using Tabula 4.1.1
 */
public class ModelChargeTubeConnector extends ModelBase {
    public ModelRenderer curvedOutput;
    public ModelRenderer curvedOutput_1;

    public ModelChargeTubeConnector() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.curvedOutput = new ModelRenderer(this, 0, 0);
        this.curvedOutput.setRotationPoint(0.0F, 17.0F, 1.0F);
        this.curvedOutput.addBox(2.0F, -2.0F, -2.0F, 5, 2, 2, 0.0F);
        this.curvedOutput_1 = new ModelRenderer(this, 14, 0);
        this.curvedOutput_1.setRotationPoint(5.0F, 16.0F, 0.0F);
        this.curvedOutput_1.addBox(2.0F, -2.0F, -2.0F, 1, 4, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.curvedOutput.render(f5);
        this.curvedOutput_1.render(f5);
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
