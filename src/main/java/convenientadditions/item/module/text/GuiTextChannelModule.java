package convenientadditions.item.module.text;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiScreen;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.TextBox;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import java.io.IOException;

public class GuiTextChannelModule extends CAGuiScreen {
    private static final ImageResourceLocation textChannelModuleGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/proximitysensor.png",0,0,168,34);

    public TextBox textBox;
    public GuiButton btn;
    public ItemStack stack;
    public boolean mainhand;

    public GuiTextChannelModule(ItemStack stack,boolean mainhand) {
        super(textChannelModuleGuiTextures);
        this.stack=stack;
        this.mainhand=mainhand;
    }

    @Override
    public void initGui() {
        super.initGui();
        textBox=addWidget(new TextBox(0,leftX+9,topY+10,116,13));
        if(stack.hasTagCompound()&&stack.getTagCompound().hasKey("MATCHER_TEXT"))
            textBox.setText(stack.getTagCompound().getString("MATCHER_TEXT"));
        btn=addButton(new GuiButtonExt(1,leftX+129,topY+9, 30, 15, Helper.localize(ModConstants.Mod.MODID+":done")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button==btn){
            String text=textBox.getText();
            if(text.length()>20)
                text=text.substring(0,19);
            ModNetworking.INSTANCE.sendToServer(new MessageTextChannelModule(mainhand,textBox.getText()));
            Minecraft.getMinecraft().player.closeScreen();
        }
        super.actionPerformed(button);
    }
}
