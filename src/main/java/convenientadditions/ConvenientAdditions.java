package convenientadditions;

import convenientadditions.init.*;
import convenientadditions.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModConstants.Mod.MODID, version = ModConstants.Mod.VERSION, dependencies = ModConstants.Mod.DEPENDENCIES, guiFactory = ModConstants.Mod.guiFactoryClassPath)
public class ConvenientAdditions {
    @Instance(ModConstants.Mod.MODID)
    public static ConvenientAdditions INSTANCE;
    @SidedProxy(modId = ModConstants.Mod.MODID, serverSide = ModConstants.Mod.commonProxyClassPath, clientSide = ModConstants.Mod.clientProxyClassPath)
    public static CommonProxy PROXY;
    public static final CreativeTabs CREATIVETAB = new CreativeTabs(ModConstants.Mod.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.playerInterfaceBlock);
        }
    };
    public static String configFile;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
        configFile=event.getSuggestedConfigurationFile().getAbsolutePath();
        ModBlocks.init();
        ModTileEntities.init();
        ModItems.init();
        ModOredict.registerOres();
        ModRecipes.init();
        ModNetworking.init();
        ModSounds.init();
        PROXY.InitModels();
        PROXY.registerRenderers();
        ModEventHandlers.init();
        ModCAAPI.init();
        ModPotions.init();
        PROXY.initWaila();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModEntities.init();
    }
}
