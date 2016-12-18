package convenientadditions.api.gui.widget.itemView;

import convenientadditions.api.gui.widget.IWidgetDrawable;
import convenientadditions.api.gui.widget.IWidgetTooltip;
import convenientadditions.api.util.GuiHelper;
import convenientadditions.api.util.Helper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemView implements IWidgetDrawable, IWidgetTooltip {

    public ItemStack itemStack;
    int posX,posY;

    public ItemView(@Nonnull ItemStack itemStack, int posX, int posY){
        this.itemStack=itemStack;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public boolean hasTooltip(GuiScreen guiScreen) {
        return !itemStack.isEmpty();
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        return itemStack.getTooltip(Helper.getClientPlayer(),false);
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return true;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        drawItem(itemStack);
    }

    public void drawItem(ItemStack stack){
        RenderItem itemRender=GuiHelper.getRenderItem();
        itemRender.zLevel = 100.0F;

        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableDepth();
        itemRender.renderItemAndEffectIntoGUI(Helper.getClientPlayer(), stack, posX, posY);
        itemRender.renderItemOverlayIntoGUI(GuiHelper.getFontRenderer(), stack, posX, posY, null);

        itemRender.zLevel = 0.0F;
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
