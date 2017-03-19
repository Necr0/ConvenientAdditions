package convenientadditions.item.relic.transmutationTome;

import convenientadditions.ModConstants;
import convenientadditions.api.SimpleSearchQuery;
import convenientadditions.api.gui.CAGuiScreen;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.IWidget;
import convenientadditions.api.gui.widget.IWidgetKeyboardListener;
import convenientadditions.api.gui.widget.TextBox;
import convenientadditions.api.gui.widget.itemView.ItemViewMulti;
import convenientadditions.api.gui.widget.label.CenteredLabel;
import convenientadditions.api.gui.widget.label.CenteredLabelMulti;
import convenientadditions.api.gui.widget.label.Label;
import convenientadditions.api.registry.transmutationTome.ITransmutationTomeLookupProvider;
import convenientadditions.api.registry.transmutationTome.ITransmutationTomeRecipe;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;
import convenientadditions.api.util.Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiTransmutationTomeRecipeLookup extends CAGuiScreen {
    public static GuiScreen previous;

    public static List<List<ITransmutationTomeLookupProvider.Lookup>> lookups;
    public static ImageResourceLocation bg=new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/transmutationtomelookup.png",0,0,178,124);

    int page=0;
    String query="";
    boolean updated=false;
    List<List<ITransmutationTomeLookupProvider.Lookup>> currentLookups;
    TextBox textBox;
    List<IWidget> clearable;

    public GuiTransmutationTomeRecipeLookup(int page){
        super(bg);
        this.clearable=new ArrayList<>();
        generateLookupList();
        currentLookups=getMatchingLookups(query,lookups);
        this.page=page;
        updateScreen();
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        if(!updated)
            return;
        widgetList.removeIf(widget -> clearable.contains(widget));
        clearable=new ArrayList<>();
        currentLookups=getMatchingLookups(query,lookups);
        this.page=Math.max(Math.min(getPages()-1,page),0);
        initClearable();
        updated=false;
    }


    @Override
    public void initGui(){
        super.initGui();
        initClearable();
        addWidget(new Label(leftX+8,topY+6, Helper.localize("gui."+ModConstants.Mod.MODID+":transmutationTomeLookup")));
        addButton(new GuiButtonExt(0,leftX+8,topY+108,12,12,"<"));
        addButton(new GuiButtonExt(1,leftX+156,topY+108,12,12,">"));
        textBox=addWidget(new TextBox(2,leftX+73,topY+5,98,10));
        textBox.setText(query);
    }

    public void initClearable(){
        int start=page*10;
        int end=Math.min(page*10+9,currentLookups.size()-1);
        for(int i=0;i<(end-start+1);i++){
            clearable.add(addWidget(new ItemViewMulti(getBases(currentLookups.get(start+i)),leftX+(i>=5?84:0)+8,topY+18+(i%5)*18)));
            clearable.add(addWidget(new ItemViewMulti(getTransmutators(currentLookups.get(start+i)),leftX+(i>=5?84:0)+26,topY+18+(i%5)*18)));
            clearable.add(addWidget(new CenteredLabelMulti(leftX+(i>=5?84:0)+54,topY+21+(i%5)*18,getLevels(currentLookups.get(start+i)),0x009900)));
            clearable.add(addWidget(new ItemViewMulti(getResults(currentLookups.get(start+i)),leftX+(i>=5?84:0)+68,topY+18+(i%5)*18)));
        }
        if(getPages()!=0)
            clearable.add(addWidget(new CenteredLabel(leftX+86,topY+110, Helper.localize(ModConstants.Mod.MODID+":page") +": "+(page+1)+"/"+getPages())));
        else
            clearable.add(addWidget(new CenteredLabel(leftX+86,topY+110, Helper.localize(ModConstants.Mod.MODID+":page") +": 0/0")));
    }

    public int getPages(){
        return ((currentLookups.size()+9)/10);
    }

    @Override
    public void actionPerformed(GuiButton btn) {
        if(getPages()==0)
            return;
        int p=btn.id==0?-1:1;
        page=Math.floorMod(page + p,getPages());
        updated=true;
    }

    @Override
    public void messageGui(@Nullable IWidget sender, Object message){
        if(sender==textBox){
            query=(String) message;
            updated=true;
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        boolean flag=false;
        for(IWidget widget:widgetList){
            if(widget instanceof IWidgetKeyboardListener){
                if(((IWidgetKeyboardListener) widget).onKey(this,typedChar,keyCode))
                    flag=true;
            }
        }
        if(flag)
            return;
        if (keyCode == Keyboard.KEY_ESCAPE || Minecraft.getMinecraft().gameSettings.keyBindInventory.isActiveAndMatches(keyCode)) {
            if(previous!=null){
                mc.displayGuiScreen(previous);
                previous=null;
                return;
            }
        }
        super.keyTyped(typedChar,keyCode);
    }

    //////////
    //STATIC//
    //////////

    public static void generateLookupList(){
        List<List<ITransmutationTomeLookupProvider.Lookup>> lookups=new ArrayList<>();
        for(ITransmutationTomeRecipe r:TransmutationTomeRecipeHandler.INSTANCE.recipes){
            if(r instanceof ITransmutationTomeLookupProvider)
                if(((ITransmutationTomeLookupProvider) r).getLookups().size()>0)
                    lookups.add(((ITransmutationTomeLookupProvider) r).getLookups());
        }
        GuiTransmutationTomeRecipeLookup.lookups=lookups;
    }

    public static NonNullList<ItemStack> getBases(List<ITransmutationTomeLookupProvider.Lookup> ls){
        NonNullList ret=NonNullList.create();
        for(ITransmutationTomeLookupProvider.Lookup l:ls){
            ret.add(l.getBase());
        }
        return ret;
    }

    public static NonNullList<ItemStack> getTransmutators(List<ITransmutationTomeLookupProvider.Lookup> ls){
        NonNullList ret=NonNullList.create();
        for(ITransmutationTomeLookupProvider.Lookup l:ls){
            ret.add(l.getTransmutator());
        }
        return ret;
    }

    public static NonNullList<ItemStack> getResults(List<ITransmutationTomeLookupProvider.Lookup> ls){
        NonNullList ret=NonNullList.create();
        for(ITransmutationTomeLookupProvider.Lookup l:ls){
            ret.add(l.getResult());
        }
        return ret;
    }

    public static NonNullList<String> getLevels(List<ITransmutationTomeLookupProvider.Lookup> ls){
        NonNullList<String> ret=NonNullList.create();
        for(ITransmutationTomeLookupProvider.Lookup l:ls){
            ret.add(l.getLevel()+"");
        }
        return ret;
    }

    public static NonNullList<List<ITransmutationTomeLookupProvider.Lookup>> getMatchingLookups(String query,List<List<ITransmutationTomeLookupProvider.Lookup>> lls){
        NonNullList<List<ITransmutationTomeLookupProvider.Lookup>> ret=NonNullList.create();

        for(List<ITransmutationTomeLookupProvider.Lookup> ls:lls) {
            List<String> s = new ArrayList<>();
            for (ITransmutationTomeLookupProvider.Lookup l : ls) {
                s.add(l.getBase().getDisplayName()+"§"+l.getTransmutator().getDisplayName()+"§"+l.getResult().getDisplayName()+" ");
            }
            if(SimpleSearchQuery.getQueryResult(query,s,false).size()>0)
                ret.add(ls);
        }
        return ret;
    }
}
