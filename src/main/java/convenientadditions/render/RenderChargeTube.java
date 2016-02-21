package convenientadditions.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

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
            ResourceLocation test = new ResourceLocation(ConvenientAdditionsMod.MODID+":textures/models/ChargeTube.png");
            ResourceLocation test_1 = new ResourceLocation(ConvenientAdditionsMod.MODID+":textures/models/TubeConnector.png");
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(test);
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            this.modelTube.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            TileEntityChargeTube t=(TileEntityChargeTube) tileEntity;
            for(ForgeDirection d:ForgeDirection.VALID_DIRECTIONS){
            	if(t.isConnected(d)){
	                GL11.glPushMatrix();
	                FMLClientHandler.instance().getClient().renderEngine.bindTexture(test_1);
	                GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
	                GL11.glPushMatrix();
	                GL11.glRotatef(180F, 0F, 0F, 1.0F);
	                switch(d){
	                	case UP:
	    	                GL11.glTranslatef(-1f, 1f, 0f);
	                        GL11.glRotatef(90f, 0f, 0f, -1f);
	                		break;
	                	case DOWN:
	    	                GL11.glTranslatef(1f, 1f, 0f);
	                        GL11.glRotatef(90f, 0f, 0f, 1f);
	                		break;
	                	case NORTH:
	                        GL11.glRotatef(90f, 0f, 1f, 0f);
	                		break;
	                	case EAST:
	    	                GL11.glTranslatef(0f, 2f, 0f);
	                        GL11.glRotatef(180f, 0f, 0f, 1f);
	                		break;
	                	case SOUTH:
	                        GL11.glRotatef(90f, 0f, -1f, 0f);
	                		break;
	                	case WEST:
	                        GL11.glRotatef(0f, 0f, 0f, 0f);
	                		break;
	                	default:
	                		break;
	                }
	                this.modelConnector.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	                GL11.glPopMatrix();
	                GL11.glPopMatrix();
            	}
            }
        }
    }
}