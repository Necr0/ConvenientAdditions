package convenientadditions.base.item;

import convenientadditions.ModConstants;
import convenientadditions.api.util.Helper;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by Necro on 3/8/2017.
 */
public enum EnumItemCategory {
    CRAFTING_MATERIAL("craftingMaterial"),
    TRINKET("trinket"),
    RELIC("relic"),
    TOOL("tool"),
    CONSUMABLE("consumable"),
    MACHINE("machine"),
    MODULE("module"),
    MISC("misc");

    public String name;

    EnumItemCategory(String name){
        this.name=name;
    }

    public String getSuffix(){
        return name;
    }

    public String getUnlocalizedName(){
        return "tooltip."+ModConstants.Mod.MODID+":itemCategory."+getSuffix();
    }

    public String getLocalizedName(){
        return Helper.localize(getUnlocalizedName());
    }

    public void addTooltip(List<String> tooltips){
        tooltips.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":itemCategory", TextFormatting.DARK_PURPLE+getLocalizedName()));
    }
}
