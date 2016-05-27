package convenientadditions;

import convenientadditions.api.ConAddAPI;
import convenientadditions.api.util.EnchantmentUtil;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModCAAPI;
import convenientadditions.init.ModItems;
import convenientadditions.init.ModNetworking;
import convenientadditions.init.ModOredict;
import convenientadditions.init.ModRecipes;
import convenientadditions.init.ModThaumcraftAspects;
import convenientadditions.proxy.CommonProxy;
import convenientadditions.worldgen.OreTitaniumWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ConvenientAdditionsMod.MODID, version = ConvenientAdditionsMod.VERSION,dependencies = ConvenientAdditionsMod.DEPENDENCIES)
public class ConvenientAdditionsMod
{
    public static final String MODID = "convenientadditions";
    public static final String VERSION = "1.0";
    public static final String DEPENDENCIES = "required-after:Baubles;required-after:conveniencecore;after:Thaumcraft";
    @Instance(ConvenientAdditionsMod.MODID)
    public static ConvenientAdditionsMod INSTANCE;
    
    public static final ToolMaterial TOOLMATERIAL_TITANIUM=EnumHelper.addToolMaterial("TITANIUM", 3, 906, 7F, 2.3F, 20);
    
	public static boolean thaumcraftLoaded = false;
    
    @SidedProxy(modId=MODID,serverSide=Reference.commonProxyClassPath,clientSide=Reference.clientProxyClassPath)
    public static CommonProxy PROXY;
    public static CreativeTabs CREATIVETAB=new CreativeTabs(MODID) {
		@Override
		public Item getTabIconItem() {
			return ItemBlock.getItemFromBlock(ModBlocks.playerInterfaceBlock);
		}
	};
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	ConAddAPI.init();
    	ModBlocks.init();
    	ModItems.init();
    	PROXY.InitModels();
    	ModOredict.registerOres();
    	ModRecipes.init();
    	ModNetworking.init();
    	EnchantmentUtil.init();
    	PROXY.registerTileEntities();
    	PROXY.registerRenderers();
    	ModCAAPI.init();
    	this.TOOLMATERIAL_TITANIUM.setRepairItem(new ItemStack(ModItems.ingotTitanium));

    	thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
    }
    

    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//PROXY.InitRendering();
    	PROXY.registerEntities();
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	GameRegistry.registerWorldGenerator( new OreTitaniumWorldGen(), 0 );
    	ModThaumcraftAspects.init();
    }
}
