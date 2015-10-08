package convenientadditions.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelComposter - Necr0
 * Created using Tabula 4.1.1
 */
public class ModelComposter extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape5;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;

    public ModelComposter() {
        this.textureWidth = 64;
        this.textureHeight = 33;
        this.shape5 = new ModelRenderer(this, 0, 16);
        this.shape5.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 0, 0);
        this.shape1_3.setRotationPoint(16.0F, 0.0F, 0.0F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 15, 15, 1, 0.0F);
        this.setRotateAngle(shape1_3, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-8.0F, 8.0F, -8.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 15, 15, 1, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 0, 0);
        this.shape1_2.setRotationPoint(16.0F, 0.0F, 0.0F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 15, 15, 1, 0.0F);
        this.setRotateAngle(shape1_2, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(16.0F, 0.0F, 0.0F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 15, 15, 1, 0.0F);
        this.setRotateAngle(shape1_1, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape1.addChild(this.shape5);
        this.shape1_2.addChild(this.shape1_3);
        this.shape1_1.addChild(this.shape1_2);
        this.shape1.addChild(this.shape1_1);
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
