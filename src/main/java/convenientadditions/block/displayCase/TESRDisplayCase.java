package convenientadditions.block.displayCase;

import convenientadditions.api.util.GuiHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Created by Necro on 3/10/2017.
 */
@SideOnly(Side.CLIENT)
public class TESRDisplayCase extends TileEntitySpecialRenderer<TileEntityDisplayCase> {
    RenderItem itemRenderer;

    @Override
    public void renderTileEntityAt(TileEntityDisplayCase t, double x, double y, double z, float partialTicks, int dest) {
        if(itemRenderer==null)
            itemRenderer=GuiHelper.getRenderItem();
        if (!t.inventory.getStackInSlot(0).isEmpty()) {
            GL11.glPushMatrix();
            GlStateManager.translate(x+.5, y+.2, z+.5);
            GlStateManager.scale(1.5f,1.5f,1.5f);
            float rotation = (System.currentTimeMillis() % 16000) * .045f;
            GlStateManager.rotate(rotation,0,1,0);
            //RenderEntityItem
            this.itemRenderer.renderItem(t.inventory.getStackInSlot(0), ItemCameraTransforms.TransformType.GROUND);
            GL11.glPopMatrix();
        }
    }
}
