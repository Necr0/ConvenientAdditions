package convenientadditions.block.misc.composter.render;

import convenientadditions.ModConstants;
import convenientadditions.block.misc.composter.TileEntityComposter;
import convenientadditions.config.ModConfigMisc;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class RenderComposter extends TileEntitySpecialRenderer<TileEntityComposter> {
    private ModelComposterContent modelComposterContent = new ModelComposterContent();

    @Override
    public void renderTileEntityAt(TileEntityComposter t, double d0, double d1, double d2, float f, int dest) {
        if (t.content > 0) {
            GL11.glPushMatrix();
            float d = (float) t.content / ModConfigMisc.composter_capacity;
            if (d > 1)
                d = 1.02F;
            d *= .88F;
            GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + 1.5F + d, (float) d2 + 0.5F);
            ResourceLocation test = new ResourceLocation(ModConstants.Mod.MODID + ":textures/models/ComposterContent.png");
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(test);
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            this.modelComposterContent.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }
}