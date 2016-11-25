package convenientadditions;

import convenientadditions.init.*;
import convenientadditions.proxy.CommonProxy;
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
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModConstants.Mod.MODID, version = ModConstants.Mod.VERSION, dependencies = ModConstants.Mod.DEPENDENCIES)
public class ConvenientAdditions {
    public static final ToolMaterial TOOLMATERIAL_TITANIUM = EnumHelper.addToolMaterial("TITANIUM", 3, 906, 7F, 2.3F, 20);
    @Instance(ModConstants.Mod.MODID)
    public static ConvenientAdditions INSTANCE;
    @SidedProxy(modId = ModConstants.Mod.MODID, serverSide = ModConstants.Mod.commonProxyClassPath, clientSide = ModConstants.Mod.clientProxyClassPath)
    public static CommonProxy PROXY;
    public static CreativeTabs CREATIVETAB = new CreativeTabs(ModConstants.Mod.MODID) {
        @Override
        public Item getTabIconItem() {
            return ItemBlock.getItemFromBlock(ModBlocks.playerInterfaceBlock);
        }
    };

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ModConfig.init();
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
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.registerEntities();
        PROXY.initWaila();
    }
}
