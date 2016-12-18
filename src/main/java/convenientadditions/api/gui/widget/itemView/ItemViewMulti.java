package convenientadditions.api.gui.widget.itemView;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemViewMulti extends ItemView{

    public NonNullList<ItemStack> itemStacks;

    public ItemViewMulti(NonNullList<ItemStack> itemStacks, int posX, int posY){
        super(itemStacks.get(0),posX,posY);
        this.itemStacks=itemStacks;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        int i= (int) ((Minecraft.getSystemTime()/1000)%itemStacks.size());
        drawItem(itemStacks.get(i));
    }
}
