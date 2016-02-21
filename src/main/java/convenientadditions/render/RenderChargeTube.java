package convenientadditions.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.render.model.ModelChargeTube;
import convenientadditions.render.model.ModelChargeTubeConnector;
import convenientadditions.tileentity.TileEntityChargeTube;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderChargeTube extends TileEntitySpecialRenderer
{
    private ModelChargeTube modelTube = new ModelChargeTube();
    private ModelChargeTubeConnector modelConnector = new ModelChargeTubeConnector();

    public RenderChargeTube(){}

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double d0, double d1, double d2, float f)
    {
        if (tileEntity instanceof TileEntityChargeTube)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
            ResourceLocation test = new ResourceLocation(ConvenientAdditionsMod.MODID+":textures/models/Composter.png");
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(test);
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            this.modelConnector.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            TileEntityChargeTube t=(TileEntityChargeTube) tileEntity;
            /*if(t.content>0){
	            GL11.glPushMatrix();
	            float d=(float)t.content/(float)t.capacity;
	            if(d>1)
	            	d=1.02F;
	            d*=.88F;
	            GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + 1.5F + d, (float) d2 + 0.5F);
	            test = new ResourceLocation(ConvenientAdditionsMod.MODID+":textures/models/ComposterContent.png");
	            FMLClientHandler.instance().getClient().renderEngine.bindTexture(test);
	            GL11.glPushMatrix();
	            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
	            this.modelComposterContent.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	            GL11.glPopMatrix();
	            GL11.glPopMatrix();
        	}*/
        }
    }
}