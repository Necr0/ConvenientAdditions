package convenientadditions;

import convenientadditions.api.ConAddAPI;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModCAAPI;
import convenientadditions.init.ModConfig;
import convenientadditions.init.ModItems;
import convenientadditions.init.ModNetworking;
import convenientadditions.init.ModOredict;
import convenientadditions.init.ModRecipes;
import convenientadditions.init.ModSounds;
import convenientadditions.init.ModTileEntities;
import convenientadditions.proxy.CommonProxy;
import convenientadditions.worldgen.WorldGenPortal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModConstants.Mod.MODID, version = ModConstants.Mod.VERSION, dependencies = ModConstants.Mod.DEPENDENCIES)
public class ConvenientAdditions
{
    @Instance(ModConstants.Mod.MODID)
    public static ConvenientAdditions INSTANCE;
    
    public static final ToolMaterial TOOLMATERIAL_TITANIUM=EnumHelper.addToolMaterial("TITANIUM", 3, 906, 7F, 2.3F, 20);
    
	public static boolean thaumcraftLoaded = false;
    
    @SidedProxy(modId=ModConstants.Mod.MODID,serverSide=ModConstants.Mod.commonProxyClassPath,clientSide=ModConstants.Mod.clientProxyClassPath)
    public static CommonProxy PROXY;
    public static CreativeTabs CREATIVETAB=new CreativeTabs(ModConstants.Mod.MODID) {
		@Override
		public Item getTabIconItem() {
			return ItemBlock.getItemFromBlock(ModBlocks.playerInterfaceBlock);
		}
	};
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	ModConfig.init();
    	ConAddAPI.init();
    	ModBlocks.init();
    	ModTileEntities.init();
    	ModItems.init();
    	ModOredict.registerOres();
    	ModRecipes.init();
    	ModNetworking.init();
    	ModSounds.init();
    	PROXY.InitModels();
    	PROXY.registerRenderers();
    	PROXY.registerEventHandlers();
    	ModCAAPI.init();
    	WorldGenPortal.init();
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
    }
}
