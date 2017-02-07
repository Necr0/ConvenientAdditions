package convenientadditions.guide;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiScreen;
import convenientadditions.api.gui.ImageResourceLocation;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

public class GuiGuide extends CAGuiScreen {
    public JsonObject pages;
    private List<ITextComponent> cachedComponents;
    private String cachedPage;
    private String currPage;

    private static final ImageResourceLocation guideGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/guidebook.png",0,0,240,180);

    public GuiGuide() {
        super(guideGuiTextures);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (!this.cachedPage.equals(this.currPage))
        {
            try
            {
                ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(pages.get(currPage).getAsString());
                this.cachedComponents = itextcomponent != null ? GuiUtilRenderComponents.splitText(itextcomponent, 116, this.fontRenderer, true, true) : null;
            }
            catch (JsonParseException var13)
            {
                this.cachedComponents = null;
            }
            this.cachedPage = this.currPage;
        }

        if(cachedComponents!=null){
            int k1 = Math.min(128 / this.fontRenderer.FONT_HEIGHT, this.cachedComponents.size());

            for (int i = 0; i < k1; ++i)
            {
                ITextComponent itextcomponent2 = this.cachedComponents.get(i);
                this.fontRenderer.drawString(itextcomponent2.getUnformattedText(), i + 36, 34 + i * this.fontRenderer.FONT_HEIGHT, 0);
            }

            ITextComponent itextcomponent1 = this.getClickedComponentAt(mouseX, mouseY);

            if (itextcomponent1 != null)
            {
                this.handleComponentHover(itextcomponent1, mouseX, mouseY);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (mouseButton == 0)
        {
            ITextComponent itextcomponent = this.getClickedComponentAt(mouseX, mouseY);

            if (itextcomponent != null && this.handleComponentClick(itextcomponent))
            {
                return;
            }
        }

    }

    @Override
    public boolean handleComponentClick(ITextComponent component)
    {
        ClickEvent clickevent = component.getStyle().getClickEvent();

        if (clickevent == null)
        {
            return false;
        }
        else if (clickevent.getAction() == ClickEvent.Action.CHANGE_PAGE)
        {
            this.currPage = clickevent.getValue();
            return true;
        }
        else
        {
            boolean flag = super.handleComponentClick(component);

            if (flag && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND)
            {
                this.mc.displayGuiScreen(null);
            }

            return flag;
        }
    }

    @Nullable
    public ITextComponent getClickedComponentAt(int p_175385_1_, int p_175385_2_)
    {
        if (this.cachedComponents == null)
        {
            return null;
        }
        else
        {
            int i = p_175385_1_ - (this.width - 192) / 2 - 36;
            int j = p_175385_2_ - 2 - 16 - 16;

            if (i >= 0 && j >= 0)
            {
                int k = Math.min(128 / this.fontRenderer.FONT_HEIGHT, this.cachedComponents.size());

                if (i <= 116 && j < this.mc.fontRenderer.FONT_HEIGHT * k + k)
                {
                    int l = j / this.mc.fontRenderer.FONT_HEIGHT;

                    if (l >= 0 && l < this.cachedComponents.size())
                    {
                        ITextComponent itextcomponent = this.cachedComponents.get(l);
                        int i1 = 0;

                        for (ITextComponent itextcomponent1 : itextcomponent)
                        {
                            if (itextcomponent1 instanceof TextComponentString)
                            {
                                i1 += this.mc.fontRenderer.getStringWidth(((TextComponentString)itextcomponent1).getText());

                                if (i1 > i)
                                {
                                    return itextcomponent1;
                                }
                            }
                        }
                    }

                    return null;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

}
