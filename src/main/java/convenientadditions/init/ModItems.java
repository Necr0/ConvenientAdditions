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
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
        registerItem(ingotTitanium,Reference.ingotTitaniumItemName);
        registerItem(nuggetTitanium,Reference.nuggetTitaniumItemName);
        registerItem(itemDirtChunk,Reference.dirtChunkItemName);
    	//ttools
        registerItem(itemTitaniumPickaxe,Reference.pickaxeTitaniumItemName);
        registerItem(itemTitaniumAxe,Reference.axeTitaniumItemName);
        registerItem(itemTitaniumSpade,Reference.spadeTitaniumItemName);
        registerItem(itemTitaniumHoe,Reference.hoeTitaniumItemName);
        registerItem(itemTitaniumSword,Reference.swordTitaniumItemName);
        registerItem(itemTitaniumWrench,Reference.titaniumWrenchItemName);
        //misc
        registerItem(itemFertilizer,Reference.fertilizerItemName);
        registerItem(itemCompost,Reference.compostItemName);
        registerItem(itemSunstone,Reference.sunstoneItemName);
        registerItem(itemBlazingRock,Reference.blazingRockItemName);
        //baubles
        registerItem(itemSunlightRing,Reference.sunlightRingItemName);
        registerItem(itemSaturationRing,Reference.saturationRingItemName);
        registerItem(itemBreathAmulet,Reference.breathAmuletItemName);
        registerItem(itemChargingRing,Reference.chargingRingItemName);
        registerItem(itemFloatingBelt,Reference.floatingBeltItemName);
        //goo
        //ItemGoo.init();
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModelMeshers()
    {
    	ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(ingotTitanium,0,new ModelResourceLocation(ingotTitanium.getUnlocalizedName().substring(5)));
		mesher.register(nuggetTitanium,0,new ModelResourceLocation(nuggetTitanium.getUnlocalizedName().substring(5)));
		mesher.register(itemDirtChunk,0,new ModelResourceLocation(itemDirtChunk.getUnlocalizedName().substring(5)));
    	//ttools
		registerMesh(itemTitaniumPickaxe,itemTitaniumPickaxe.getModelResourceLocation());
		registerMesh(itemTitaniumAxe,itemTitaniumAxe.getModelResourceLocation());
		registerMesh(itemTitaniumSpade,itemTitaniumSpade.getModelResourceLocation());
		registerMesh(itemTitaniumHoe,itemTitaniumHoe.getModelResourceLocation());
		registerMesh(itemTitaniumSword,itemTitaniumSword.getModelResourceLocation());
		registerMesh(itemTitaniumWrench,itemTitaniumWrench.getModelResourceLocation());
        //misc
		registerMesh(itemFertilizer,itemFertilizer.getModelResourceLocation());
		registerMesh(itemCompost,itemCompost.getModelResourceLocation());
		registerMesh(itemSunstone,itemSunstone.getModelResourceLocation());
		registerMesh(itemBlazingRock,itemBlazingRock.getModelResourceLocation());
        //baubles
		registerMesh(itemSunlightRing,itemSunlightRing.getModelResourceLocation());
		registerMesh(itemSaturationRing,itemSaturationRing.getModelResourceLocation());
		registerMesh(itemBreathAmulet,itemBreathAmulet.getModelResourceLocation());
		registerMesh(itemChargingRing,itemChargingRing.getModelResourceLocation());
		registerMesh(itemFloatingBelt,itemFloatingBelt.getModelResourceLocation());
        //goo
    }
    
    public static void registerItem(Item item,String registryName)
    {
    	if(item.getRegistryName()==null)
    		item.setRegistryName(registryName);
    	GameRegistry.register(item);
    }
    
    public static void registerMesh(Item item,ModelResourceLocation location)
    {
    	ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
    	mesher.register(item,0,location);
    }
}
