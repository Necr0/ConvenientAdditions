package convenientadditions;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.init.ModOredict;
import convenientadditions.init.ModRecipes;
import convenientadditions.init.ModThaumcraftAspects;
import convenientadditions.init.Reference;
import convenientadditions.proxy.CommonProxy;
import convenientadditions.worldgen.OreTitaniumWorldGen;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ConvenientAdditionsMod.MODID, version = ConvenientAdditionsMod.VERSION,dependencies = ConvenientAdditionsMod.DEPENDENCIES)
public class ConvenientAdditionsMod
{
    public static final String MODID = "convenientadditions";
    public static final String VERSION = "1.0";
    public static final String DEPENDENCIES = "required-after:Baubles;after:Thaumcraft";
    @Instance(ConvenientAdditionsMod.MODID)
    public static ConvenientAdditionsMod INSTANCE;
    
    public static final ToolMaterial TOOLMATERIAL_TITANIUM=EnumHelper.addToolMaterial("TITANIUM", 3, 906, 7F, 2.3F, 20);
    
	public static boolean thaumcraftLoaded = false;
	public static boolean baublesLoaded = false;
    
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
    	ModBlocks.init();
    	ModItems.init();
    	ModOredict.registerOres();
    	ModRecipes.init();
    	PROXY.registerTileEntities();
    	PROXY.registerRenderers();
    	PROXY.registerTickHandlers();
    	this.TOOLMATERIAL_TITANIUM.setRepairItem(new ItemStack(ModItems.ingotTitanium));

    	thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
    	baublesLoaded = Loader.isModLoaded("Baubles");
    }
    

    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	PROXY.InitRendering();
    	PROXY.registerEntities();
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	GameRegistry.registerWorldGenerator( new OreTitaniumWorldGen(), 0 );

    	ModThaumcraftAspects.init();
    }
}
