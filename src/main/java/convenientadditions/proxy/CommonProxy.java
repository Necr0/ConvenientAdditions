package convenientadditions.proxy;

import net.minecraft.world.World;
import convenientadditions.init.Reference;
import convenientadditions.init.TickHandler;
import convenientadditions.tileentity.TileEntityComposter;
import convenientadditions.tileentity.TileEntityCub3dFrame;
import convenientadditions.tileentity.TileEntityPlayerInterface;
import convenientadditions.tileentity.TileEntityPowderKeg;
import convenientadditions.tileentity.TileEntityProximitySensor;
import cpw.mods.fml.common.FMLCommonHandler;
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
        GameRegistry.registerTileEntity(TileEntityCub3dFrame.class, Reference.frameBlockName);
        GameRegistry.registerTileEntity(TileEntityComposter.class, Reference.composterBlockName);
        GameRegistry.registerTileEntity(TileEntityPowderKeg.class, Reference.powderKegBlockName);
        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, Reference.playerInterfaceBlockName);
        GameRegistry.registerTileEntity(TileEntityProximitySensor.class, Reference.proximitySensorBlockName);
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
