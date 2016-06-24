package convenientadditions.init;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.item.ItemCompost;
import convenientadditions.item.ItemFertilizer;
import convenientadditions.item.ItemLaunchingArrow;
import convenientadditions.item.charge.ItemBlazingRock;
import convenientadditions.item.charge.ItemSunstone;
import convenientadditions.item.charge.baubles.ItemBreathAmulet;
import convenientadditions.item.charge.baubles.ItemChargingRing;
import convenientadditions.item.charge.baubles.ItemSaturationRing;
import convenientadditions.item.charge.baubles.ItemSunlightRing;
import convenientadditions.item.charge.enderSlate.ItemEnderSlate;
import convenientadditions.item.tools.ItemTitaniumAxe;
import convenientadditions.item.tools.ItemTitaniumHoe;
import convenientadditions.item.tools.ItemTitaniumPickaxe;
import convenientadditions.item.tools.ItemTitaniumSpade;
import convenientadditions.item.tools.ItemTitaniumSword;
import convenientadditions.item.tools.ItemTitaniumWrench;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(ConvenientAdditions.MODID)
public class ModItems {
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemSunstone itemSunstone = new ItemSunstone();
    public static final ItemBlazingRock itemBlazingRock = new ItemBlazingRock();
    public static final ItemEnderSlate itemEnderSlate = new ItemEnderSlate();
    public static final ItemLaunchingArrow itemLaunchingArrow = new ItemLaunchingArrow();
    //dummy
    public static final Item ingotTitanium=new Item().setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.ingotTitaniumItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item nuggetTitanium=new Item().setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.nuggetTitaniumItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    public static final Item itemDirtChunk=new Item().setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.dirtChunkItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
    //baubles
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemBreathAmulet itemBreathAmulet = new ItemBreathAmulet();
    public static final ItemChargingRing itemChargingRing = new ItemChargingRing();
    //ttools
    public static final ItemTitaniumAxe itemTitaniumAxe=new ItemTitaniumAxe(ConvenientAdditions.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumPickaxe itemTitaniumPickaxe=new ItemTitaniumPickaxe(ConvenientAdditions.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumSpade itemTitaniumSpade=new ItemTitaniumSpade(ConvenientAdditions.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumHoe itemTitaniumHoe=new ItemTitaniumHoe(ConvenientAdditions.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumSword itemTitaniumSword=new ItemTitaniumSword(ConvenientAdditions.TOOLMATERIAL_TITANIUM);
    public static final ItemTitaniumWrench itemTitaniumWrench=new ItemTitaniumWrench();
    public static final Item itemIronWrench=new ItemTitaniumWrench().setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.ironWrenchItemName);
    
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
        registerItem(itemIronWrench,Reference.ironWrenchItemName);
        //misc
        registerItem(itemFertilizer,Reference.fertilizerItemName);
        registerItem(itemCompost,Reference.compostItemName);
        registerItem(itemSunstone,Reference.sunstoneItemName);
        registerItem(itemBlazingRock,Reference.blazingRockItemName);
        registerItem(itemEnderSlate,Reference.enderSlateItemName);
        registerItem(itemLaunchingArrow,Reference.launchingArrowItemName);
        //baubles
        registerItem(itemSunlightRing,Reference.sunlightRingItemName);
        registerItem(itemSaturationRing,Reference.saturationRingItemName);
        registerItem(itemBreathAmulet,Reference.breathAmuletItemName);
        registerItem(itemChargingRing,Reference.chargingRingItemName);
        //registerItem(itemFloatingBelt,Reference.floatingBeltItemName);
        //goo
        //ItemGoo.init();
        
        initModelLoader();
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModelLoader()
    {
		registerModelLocation(ingotTitanium,new ModelResourceLocation(ingotTitanium.getUnlocalizedName().substring(5),"inventory"));
		registerModelLocation(nuggetTitanium,new ModelResourceLocation(nuggetTitanium.getUnlocalizedName().substring(5),"inventory"));
		registerModelLocation(itemDirtChunk,new ModelResourceLocation(itemDirtChunk.getUnlocalizedName().substring(5),"inventory"));
    	//ttools
		registerModelLocation(itemTitaniumPickaxe,itemTitaniumPickaxe.getModelResourceLocation());
		registerModelLocation(itemTitaniumAxe,itemTitaniumAxe.getModelResourceLocation());
		registerModelLocation(itemTitaniumSpade,itemTitaniumSpade.getModelResourceLocation());
		registerModelLocation(itemTitaniumHoe,itemTitaniumHoe.getModelResourceLocation());
		registerModelLocation(itemTitaniumSword,itemTitaniumSword.getModelResourceLocation());
		registerModelLocation(itemTitaniumWrench,itemTitaniumWrench.getModelResourceLocation());
		registerModelLocation(itemIronWrench,((IModelResourceLocationProvider)itemIronWrench).getModelResourceLocation());
        //misc
		registerModelLocation(itemFertilizer,itemFertilizer.getModelResourceLocation());
		registerModelLocation(itemSunstone,0,new ModelResourceLocation(itemSunstone.getResourceLocation()+"_inactive"));
		registerModelLocation(itemSunstone,1,new ModelResourceLocation(itemSunstone.getResourceLocation()+"_active"));
		registerIndependentModelLocation(itemCompost,itemCompost.getModelResourceLocation());
		registerModelLocation(itemBlazingRock,itemBlazingRock.getModelResourceLocation());
		registerModelLocation(itemEnderSlate,0,new ModelResourceLocation(itemEnderSlate.getResourceLocation()+"_inactive","inventory"));
		registerModelLocation(itemEnderSlate,1,new ModelResourceLocation(itemEnderSlate.getResourceLocation()+"_active","inventory"));
		registerVariants(itemLaunchingArrow,itemLaunchingArrow.getModelResourceLocations());
        //baubles
		registerModelLocation(itemSunlightRing,itemSunlightRing.getModelResourceLocation());
		registerModelLocation(itemSaturationRing,itemSaturationRing.getModelResourceLocation());
		registerModelLocation(itemBreathAmulet,itemBreathAmulet.getModelResourceLocation());
		registerModelLocation(itemChargingRing,itemChargingRing.getModelResourceLocation());
		//registerModelLocation(itemFloatingBelt,itemFloatingBelt.getModelResourceLocation());
        //goo
    }
    
    public static void registerItem(Item item,String registryName)
    {
    	if(item.getRegistryName()==null)
    		item.setRegistryName(registryName);
    	GameRegistry.register(item);
    }
    
    public static void registerModelLocation(Item item,ModelResourceLocation location)
    {
    	ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }
    
    public static void registerVariants(Item item,ModelResourceLocation[] locations)
    {
    	for(int i=0;i<locations.length;i++){
    		registerModelLocation(item, i, locations[i]);
    	}
    }
    
    public static void registerModelLocation(Item item,int damage,ModelResourceLocation location)
    {
    	ModelLoader.setCustomModelResourceLocation(item, damage, location);
    }
    
    public static void registerIndependentModelLocation(Item item,ModelResourceLocation location)
    {
    	ModelLoader.setCustomMeshDefinition(item, new SimpleItemMeshDefinition(location));;
    }
    
    public static class SimpleItemMeshDefinition implements ItemMeshDefinition{
    	ModelResourceLocation location;
    	
    	public SimpleItemMeshDefinition(ModelResourceLocation location){
    		this.location=location;
    	}
    	
		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack) {
			return location;
		}
    }
    
    public static void registerItemBlockModel(Block block,ModelResourceLocation location)
    {
    	ModItems.registerModelLocation(ItemBlock.getItemFromBlock(block),location);
    }
}
