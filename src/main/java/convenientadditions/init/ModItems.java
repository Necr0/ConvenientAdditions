package convenientadditions.init;

import net.minecraft.item.Item;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.item.ItemCompost;
import convenientadditions.item.ItemFertilizer;
import convenientadditions.item.ItemRedstonePulseEmitter;
import convenientadditions.item.ItemSunstone;
import convenientadditions.item.baubles.ItemBreathAmulet;
import convenientadditions.item.baubles.ItemChargingRing;
import convenientadditions.item.baubles.ItemSaturationRing;
import convenientadditions.item.baubles.ItemSunlightRing;
import convenientadditions.item.slime.ItemGoo;
import convenientadditions.item.tools.ItemTitaniumAxe;
import convenientadditions.item.tools.ItemTitaniumHoe;
import convenientadditions.item.tools.ItemTitaniumPickaxe;
import convenientadditions.item.tools.ItemTitaniumSpade;
import convenientadditions.item.tools.ItemTitaniumSword;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModItems {
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemSunstone itemSunstone = new ItemSunstone();
    public static final ItemRedstonePulseEmitter itemRedstonePulseEmitter = new ItemRedstonePulseEmitter();
    //dummy
    public static final Item ingotTitanium=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.ingotTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_ingot");
    public static final Item nuggetTitanium=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.nuggetTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_nugget");
    public static final Item itemDirtChunk=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    //baubles
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemBreathAmulet itemBreathAmulet = new ItemBreathAmulet();
    public static final ItemChargingRing itemChargingRing = new ItemChargingRing();
    //ttools
    public static final Item itemTitaniumAxe=new ItemTitaniumAxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_axe");
    public static final Item itemTitaniumPickaxe=new ItemTitaniumPickaxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.pickaxeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_pickaxe");
    public static final Item itemTitaniumSpade=new ItemTitaniumSpade(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.spadeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_spade");
    public static final Item itemTitaniumHoe=new ItemTitaniumHoe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.hoeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_hoe");
    public static final Item itemTitaniumSword=new ItemTitaniumSword(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.swordTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB).setTextureName(ConvenientAdditionsMod.MODID+":titanium_sword");
    
    public static void init()
    {
    	//dummy
        GameRegistry.registerItem(ingotTitanium,Reference.ingotTitaniumItemName);
        GameRegistry.registerItem(nuggetTitanium,Reference.nuggetTitaniumItemName);
        GameRegistry.registerItem(itemDirtChunk,Reference.dirtChunkItemName);
    	//ttools
        GameRegistry.registerItem(itemTitaniumPickaxe,Reference.pickaxeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumAxe,Reference.axeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumSpade,Reference.spadeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumHoe,Reference.hoeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumSword,Reference.swordTitaniumItemName);
        //misc
        GameRegistry.registerItem(itemFertilizer,Reference.fertilizerItemName);
        GameRegistry.registerItem(itemCompost,Reference.compostItemName);
        GameRegistry.registerItem(itemSunstone,Reference.sunstoneItemName);
        GameRegistry.registerItem(itemRedstonePulseEmitter,Reference.redstonePulseEmitterItemName);
        //baubles
        GameRegistry.registerItem(itemSunlightRing,Reference.sunlightRingItemName);
        GameRegistry.registerItem(itemSaturationRing,Reference.saturationRingItemName);
        GameRegistry.registerItem(itemBreathAmulet,Reference.breathAmuletItemName);
        GameRegistry.registerItem(itemChargingRing,Reference.chargingRingItemName);
        //goo
        ItemGoo.init();
    }
}
