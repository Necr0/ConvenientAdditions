package convenientadditions.proxy;

import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.TickHandler;
import convenientadditions.item.slime.EntityGooItem;
import convenientadditions.tileentity.TileEntityComposter;
import convenientadditions.tileentity.TileEntityPlayerInterface;
import convenientadditions.tileentity.TileEntityPowderKeg;
import convenientadditions.tileentity.TileEntityProximitySensor;
import convenientadditions.tileentity.TileEntitySunlightCollector;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
    public void registerRenderers()
    {
    }
    
    public void registerPostSideObjects()
    {
    }

    public void registerEntities()
    {
    	EntityRegistry.registerModEntity(EntityGooItem.class, Reference.gooItemEntityName, Reference.gooItemEntityId, ConvenientAdditionsMod.INSTANCE, 128, 5, true);
    }

    public World getClientWorld()
    {
        return null;
    }

    public void registerActions()
    {
    }

    public void registerEvents()
    {
    }

    public void registerSoundHandler()
    {
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityComposter.class, Reference.composterBlockName);
        GameRegistry.registerTileEntity(TileEntityPowderKeg.class, Reference.powderKegBlockName);
        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, Reference.playerInterfaceBlockName);
        GameRegistry.registerTileEntity(TileEntityProximitySensor.class, Reference.proximitySensorBlockName);
        GameRegistry.registerTileEntity(TileEntitySunlightCollector.class, Reference.sunlightCollectorBlockName);
    }

    public void registerEntityTrackers()
    {
    }

    public void registerTickHandlers()
    {
    	FMLCommonHandler.instance().bus().register(new TickHandler());
    }

    public void InitRendering()
    {
    }
}
