package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.item.ItemCompost;
import convenientadditions.item.ItemFertilizer;
import convenientadditions.item.charge.ItemBlazingRock;
import convenientadditions.item.charge.ItemSunstone;
import convenientadditions.item.charge.baubles.ItemBreathAmulet;
import convenientadditions.item.charge.baubles.ItemChargingRing;
import convenientadditions.item.charge.baubles.ItemFloatingBelt;
import convenientadditions.item.charge.baubles.ItemSaturationRing;
import convenientadditions.item.charge.baubles.ItemSunlightRing;
import convenientadditions.item.tools.ItemTitaniumAxe;
import convenientadditions.item.tools.ItemTitaniumHoe;
import convenientadditions.item.tools.ItemTitaniumPickaxe;
import convenientadditions.item.tools.ItemTitaniumSpade;
import convenientadditions.item.tools.ItemTitaniumSword;
import convenientadditions.item.tools.ItemTitaniumWrench;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModItems {
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemSunstone itemSunstone = new ItemSunstone();
    public static final ItemBlazingRock itemBlazingRock = new ItemBlazingRock();
    //dummy
    public static final Item ingotTitanium=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.ingotTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item nuggetTitanium=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.nuggetTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item itemDirtChunk=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    //baubles
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemBreathAmulet itemBreathAmulet = new ItemBreathAmulet();
    public static final ItemChargingRing itemChargingRing = new ItemChargingRing();
    public static final ItemFloatingBelt itemFloatingBelt = new ItemFloatingBelt();
    //ttools
    public static final ItemTitaniumAxe itemTitaniumAxe=new ItemTitaniumAxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumPickaxe itemTitaniumPickaxe=new ItemTitaniumPickaxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumSpade itemTitaniumSpade=new ItemTitaniumSpade(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumHoe itemTitaniumHoe=new ItemTitaniumHoe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumSword itemTitaniumSword=new ItemTitaniumSword(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumWrench itemTitaniumWrench=new ItemTitaniumWrench();
    
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
        GameRegistry.registerItem(itemTitaniumWrench,Reference.titaniumWrenchItemName);
        //misc
        GameRegistry.registerItem(itemFertilizer,Reference.fertilizerItemName);
        GameRegistry.registerItem(itemCompost,Reference.compostItemName);
        GameRegistry.registerItem(itemSunstone,Reference.sunstoneItemName);
        GameRegistry.registerItem(itemBlazingRock,Reference.blazingRockItemName);
        //baubles
        GameRegistry.registerItem(itemSunlightRing,Reference.sunlightRingItemName);
        GameRegistry.registerItem(itemSaturationRing,Reference.saturationRingItemName);
        GameRegistry.registerItem(itemBreathAmulet,Reference.breathAmuletItemName);
        GameRegistry.registerItem(itemChargingRing,Reference.chargingRingItemName);
        GameRegistry.registerItem(itemFloatingBelt,Reference.floatingBeltItemName);
        //goo
        //ItemGoo.init();
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModelMeshers()
    {
    	ItemModelMesher mesher=Minecraft.getMinecraft().getRenderItem().getItemModelMesher();//.register(item, meta, new ModelResourceLocation("modid:itemname", "inventory"));
		//mesher.register("modid:itemname",0,"modid:itemname".getModelResourceLocation())
    	//dummy
		mesher.register(ingotTitanium,0,new ModelResourceLocation(ingotTitanium.getUnlocalizedName()));
		mesher.register(nuggetTitanium,0,new ModelResourceLocation(nuggetTitanium.getUnlocalizedName()));
		mesher.register(itemDirtChunk,0,new ModelResourceLocation(itemDirtChunk.getUnlocalizedName()));
    	//ttools
		mesher.register(itemTitaniumPickaxe,0,itemTitaniumPickaxe.getModelResourceLocation());
		mesher.register(itemTitaniumAxe,0,itemTitaniumAxe.getModelResourceLocation());
		mesher.register(itemTitaniumSpade,0,itemTitaniumSpade.getModelResourceLocation());
		mesher.register(itemTitaniumHoe,0,itemTitaniumHoe.getModelResourceLocation());
		mesher.register(itemTitaniumSword,0,itemTitaniumSword.getModelResourceLocation());
		mesher.register(itemTitaniumWrench,0,itemTitaniumWrench.getModelResourceLocation());
        //misc
		mesher.register(itemFertilizer,0,itemFertilizer.getModelResourceLocation());
		mesher.register(itemCompost,0,itemCompost.getModelResourceLocation());
		mesher.register(itemSunstone,0,itemSunstone.getModelResourceLocation());
		mesher.register(itemBlazingRock,0,itemBlazingRock.getModelResourceLocation());
        //baubles
		mesher.register(itemSunlightRing,0,itemSunlightRing.getModelResourceLocation());
		mesher.register(itemSaturationRing,0,itemSaturationRing.getModelResourceLocation());
		mesher.register(itemBreathAmulet,0,itemBreathAmulet.getModelResourceLocation());
		mesher.register(itemChargingRing,0,itemChargingRing.getModelResourceLocation());
		mesher.register(itemFloatingBelt,0,itemFloatingBelt.getModelResourceLocation());
        //goo
    }
}
