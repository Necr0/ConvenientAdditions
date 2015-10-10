package convenientadditions.proxy;

import convenientadditions.init.Reference;
import convenientadditions.tileentity.TileEntityComposter;
import convenientadditions.tileentity.TileEntityCub3dFrame;
import convenientadditions.tileentity.TileEntityPlayerInterface;
import convenientadditions.tileentity.TileEntityPowderKeg;
import net.minecraft.world.World;
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
    }

    public void registerEntityTrackers()
    {
    }

    public void registerTickHandlers()
    {
    }

    public void InitRendering()
    {
    }
}
