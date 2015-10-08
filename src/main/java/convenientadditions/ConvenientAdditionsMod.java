package convenientadditions;

import convenientadditions.init.ModBlocks;
import convenientadditions.init.ModItems;
import convenientadditions.init.ModRecipes;
import convenientadditions.init.Reference;
import convenientadditions.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ConvenientAdditionsMod.MODID, version = ConvenientAdditionsMod.VERSION)
public class ConvenientAdditionsMod
{
    public static final String MODID = "convenientadditions";
    public static final String VERSION = "1.0";
    
    @SidedProxy(modId=MODID,serverSide=Reference.commonProxyClassPath,clientSide=Reference.clientProxyClassPath)
    public static CommonProxy PROXY;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	ModBlocks.init();
    	ModRecipes.init();
    	ModItems.init();
    	PROXY.registerTileEntities();
    	PROXY.registerRenderers();
    }
    

    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	PROXY.InitRendering();
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	
    }
}
