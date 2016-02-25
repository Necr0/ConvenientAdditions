package convenientadditions.render.item;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.render.model.ModelCub3dFrame;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderCub3dFrameItem implements IItemRenderer {

	private ModelCub3dFrame modelFrame;
	
	public RenderCub3dFrameItem() {
		this.modelFrame=new ModelCub3dFrame();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type)
        {
            case ENTITY:
                return true;
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            case INVENTORY:
                return true;
            default:
                return false;
        }
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case ENTITY:
                renderFrameItem((RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
                break;
            case EQUIPPED:
                renderFrameItem((RenderBlocks) data[0], item, -0.4f, 0.50f, 0.35f);
                break;
            case EQUIPPED_FIRST_PERSON:
                renderFrameItem((RenderBlocks) data[0], item, -0.4f, 0.50f, 0.35f);
                break;
            case INVENTORY:
                renderFrameItem((RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
                break;
            default:
        }
    }

    private void renderFrameItem(RenderBlocks render, ItemStack item, float translateX, float translateY, float translateZ)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) translateX + 0.5F, (float) translateY + 1.5F, (float) translateZ + 0.5F);
        ResourceLocation test = new ResourceLocation(ConvenientAdditionsMod.MODID+":textures/models/Cub3dFrame.png");

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(test);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.modelFrame.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
