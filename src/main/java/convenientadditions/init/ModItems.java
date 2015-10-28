package convenientadditions.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.item.ItemCompost;
import convenientadditions.item.ItemDirtChunk;
import convenientadditions.item.ItemFertilizer;
import convenientadditions.item.ItemRedstonePulseEmitter;
import convenientadditions.item.ItemSunstone;
import convenientadditions.item.ItemTitaniumAxe;
import convenientadditions.item.ItemTitaniumHoe;
import convenientadditions.item.ItemTitaniumPickaxe;
import convenientadditions.item.ItemTitaniumSpade;
import convenientadditions.item.ItemTitaniumSword;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModItems {
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemDirtChunk itemDirtChunk = new ItemDirtChunk();
    public static final ItemSunstone itemSunstone = new ItemSunstone();
    public static final ItemRedstonePulseEmitter itemRedstonePulseEmitter = new ItemRedstonePulseEmitter();
    //dummy
    public static final Item ingotTitanium=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.ingotTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_ingot");

    public static final Item itemTitaniumAxe=new ItemTitaniumAxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_axe");
    public static final Item itemTitaniumPickaxe=new ItemTitaniumPickaxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.pickaxeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_pickaxe");
    public static final Item itemTitaniumSpade=new ItemTitaniumSpade(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.spadeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_spade");
    public static final Item itemTitaniumHoe=new ItemTitaniumHoe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.hoeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_hoe");
    public static final Item itemTitaniumSword=new ItemTitaniumSword(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.swordTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_sword");
    
    public static void init()
    {
        GameRegistry.registerItem(ingotTitanium,Reference.ingotTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumPickaxe,Reference.pickaxeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumAxe,Reference.axeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumSpade,Reference.spadeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumHoe,Reference.hoeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumSword,Reference.swordTitaniumItemName);
        GameRegistry.registerItem(itemFertilizer,Reference.fertilizerItemName);
        GameRegistry.registerItem(itemCompost,Reference.compostItemName);
        GameRegistry.registerItem(itemDirtChunk,Reference.dirtChunkItemName);
        GameRegistry.registerItem(itemSunstone,Reference.sunstoneItemName);
        GameRegistry.registerItem(itemRedstonePulseEmitter,Reference.redstonePulseEmitterItemName);
    }
}
